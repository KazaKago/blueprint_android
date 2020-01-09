package com.kazakago.cleanarchitecture.repository.state

import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDateTime

internal class CacheObserver(private val validTime: Duration) {

    fun <T> subscribe(load: (() -> Flow<StoreState<T>>), save: (suspend (content: StoreState<T>) -> Unit), fetch: (suspend () -> T)): Flow<StoreState<T>> {
        val loadFlow = load()
        return loadFlow
            .onStart {
                CoroutineScope(Dispatchers.IO).launch { execute(loadFlow.first(), save, fetch) }
            }
    }

    private suspend fun <T> execute(value: StoreState<T>, save: (suspend (content: StoreState<T>) -> Unit), fetch: (suspend () -> T)) {
        when (value) {
            is StoreState.Fixed -> executeFixedState(value, save, fetch)
            is StoreState.Loading -> executeLoadingState(value, save, fetch)
            is StoreState.Error -> executeErrorState(value, save, fetch)
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
