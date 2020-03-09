package com.kazakago.cleanarchitecture.data.repository.global.dispatcher

import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.global.state.StateContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FlowDispatcher<out T>(
    private val fetch: (suspend () -> T)
) {

    fun subscribe(): Flow<State<T>> {
        return flow {
            try {
                emit(State.Loading(StateContent.NotExist()))
                val newValue = fetch()
                emit(State.Fixed(StateContent.Exist(newValue)))
            } catch (exception: Exception) {
                emit(State.Error(StateContent.NotExist(), exception))
            }
        }
    }

}
