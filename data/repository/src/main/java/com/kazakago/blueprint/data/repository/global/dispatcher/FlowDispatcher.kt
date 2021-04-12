package com.kazakago.blueprint.data.repository.global.dispatcher

import com.kazakago.blueprint.domain.model.global.state.State
import com.kazakago.blueprint.domain.model.global.state.StateContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class FlowDispatcher<out ENTITY>(
    private val fetchOrigin: (suspend () -> ENTITY)
) {

    fun subscribe(): Flow<State<ENTITY>> {
        return flow {
            try {
                emit(State.Loading(StateContent.NotExist<ENTITY>()))
                val source = fetchOrigin()
                emit(State.Fixed(StateContent.Exist(source)))
            } catch (exception: Exception) {
                emit(State.Error(StateContent.NotExist<ENTITY>(), exception))
            }
        }
    }
}
