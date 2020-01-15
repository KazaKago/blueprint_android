package com.kazakago.cleanarchitecture.repository.dispatcher

import com.kazakago.cleanarchitecture.model.state.State
import com.kazakago.cleanarchitecture.model.state.StateContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FlowDispatcher<T>(
    private val fetch: (suspend () -> T)
) {

    fun subscribe(): Flow<State<T>> {
        return flow {
            try {
                emit(State.Loading(StateContent.NotStored()))
                val newValue = fetch()
                emit(State.Fixed(StateContent.Stored(newValue)))
            } catch (exception: Exception) {
                emit(State.Error(StateContent.NotStored(), exception))
            }
        }
    }

}
