package com.kazakago.cleanarchitecture.model.state

import java.time.Duration
import java.time.LocalDateTime

class StoreDistributor(private val validTime: Duration) {

    suspend fun <T> execute(load: (suspend () -> StoreState<T>), save: (suspend (content: StoreState<T>) -> Unit), fetch: (suspend () -> T)) {
        when (val storeState = load()) {
            is StoreState.Fixed -> executeFixedState(storeState, save, fetch)
            is StoreState.Loading -> executeLoadingState(storeState, save, fetch)
            is StoreState.Error -> executeErrorState(storeState, save, fetch)
        }
    }

    private suspend fun <T> executeFixedState(storedState: StoreState.Fixed<T>, save: (suspend (content: StoreState<T>) -> Unit), fetch: (suspend () -> T)) {
        when (val storeValue = storedState.value) {
            is StoreValue.Stored -> if ((storeValue.storedTime + validTime) < LocalDateTime.now()) {
                fetchNewValue(storeValue, save, fetch)
            }
            is StoreValue.NotStored -> fetchNewValue(storeValue, save, fetch)
        }
    }

    private suspend fun <T> executeLoadingState(storedState: StoreState.Loading<T>, save: (suspend (content: StoreState<T>) -> Unit), fetch: (suspend () -> T)) {
        //do nothing.
    }

    private suspend fun <T> executeErrorState(storedState: StoreState.Error<T>, save: (suspend (content: StoreState<T>) -> Unit), fetch: (suspend () -> T)) {
        fetchNewValue(storedState.value, save, fetch)
    }

    private suspend fun <T> fetchNewValue(stateValue: StoreValue<T>, save: (suspend (content: StoreState<T>) -> Unit), fetch: (suspend () -> T)) {
        try {
            save(StoreState.Loading(stateValue))
            val newValue = fetch()
            save(StoreState.Fixed(StoreValue.Stored(newValue)))
        } catch (exception: Exception) {
            save(StoreState.Error(stateValue, exception))
        }
    }

}
