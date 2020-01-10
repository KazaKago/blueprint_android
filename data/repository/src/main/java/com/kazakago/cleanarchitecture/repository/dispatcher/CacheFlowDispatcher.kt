package com.kazakago.cleanarchitecture.repository.dispatcher

import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.time.Duration

internal class CacheFlowDispatcher(private val validTime: Duration) {

    fun <T> subscribe(load: (() -> Flow<StoreState<T>>), save: (suspend (content: StoreState<T>) -> Unit), fetch: (suspend () -> T)): Flow<StoreState<T>> {
        val loadFlow = load()
        return loadFlow
            .onStart {
                CoroutineScope(Dispatchers.IO).launch { checkState(loadFlow.first(), save, fetch) }
            }
    }

    private suspend fun <T> checkState(state: StoreState<T>, save: (suspend (content: StoreState<T>) -> Unit), fetch: (suspend () -> T)) {
        when (state) {
            is StoreState.Fixed -> checkValue(state.value, save, fetch)
            is StoreState.Loading -> Unit //do nothing.
            is StoreState.Error -> fetchNewValue(state.value, save, fetch)
        }
    }

    private suspend fun <T> checkValue(storeValue: StoreValue<T>, save: (suspend (content: StoreState<T>) -> Unit), fetch: (suspend () -> T)) {
        when (storeValue) {
            is StoreValue.Stored -> if (storeValue.isExpired(validTime)) {
                fetchNewValue(storeValue, save, fetch)
            }
            is StoreValue.NotStored -> fetchNewValue(storeValue, save, fetch)
        }
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
