package com.kazakago.cleanarchitecture.repository.state

import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.StoreValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal object Observer {

    fun <T> subscribe(fetch: (suspend () -> T)): Flow<StoreState<T>> {
        return flow {
            try {
                emit(StoreState.Loading(StoreValue.NotStored()))
                val newValue = fetch()
                emit(StoreState.Fixed(StoreValue.Stored(newValue)))
            } catch (exception: Exception) {
                emit(StoreState.Error(StoreValue.NotStored(), exception))
            }
        }
    }

}
