package com.kazakago.cleanarchitecture.data.repository.global.dispatcher

import com.kazakago.cleanarchitecture.data.memory.global.DataState
import com.kazakago.cleanarchitecture.data.memory.hierarchy.state.StateMemory
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.global.state.StateContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class CacheFlowDispatcher<out ENTITY, out FETCHED_ENTITIES>(
    private val stateId: String,
    private val additionalStateIds: List<String> = emptyList(),
    private val loadEntity: (suspend () -> ENTITY?),
    private val saveEntities: (suspend (entity: FETCHED_ENTITIES) -> Unit),
    private val fetchOrigin: (suspend () -> FETCHED_ENTITIES)
) {

    var loadState: (() -> Flow<DataState>) = {
        StateMemory[stateId].asFlow()
    }
    var saveState: (suspend (stateId: String, state: DataState) -> Unit) = { stateId, state ->
        StateMemory[stateId].send(state)
    }

    fun subscribe(needRefresh: ((entity: ENTITY) -> Boolean)): Flow<State<ENTITY>> {
        return loadState()
            .onStart {
                CoroutineScope(Dispatchers.IO).launch { checkState(needRefresh) }
            }
            .map {
                mapState(it, needRefresh)
            }
    }

    suspend fun request(needRefresh: ((entity: ENTITY) -> Boolean) = { true }) {
        checkState(needRefresh)
    }

    private suspend fun mapState(dataState: DataState, needRefresh: ((entity: ENTITY) -> Boolean)): State<ENTITY> {
        val entity = loadEntity()
        val stateContent = if (entity == null || needRefresh(entity)) {
            StateContent.NotExist<ENTITY>()
        } else {
            StateContent.Exist(entity)
        }
        return when (dataState) {
            is DataState.Fixed -> State.Fixed(stateContent)
            is DataState.Loading -> State.Loading(stateContent)
            is DataState.Error -> State.Error(stateContent, dataState.exception)
        }
    }

    private suspend fun checkState(needRefresh: ((entity: ENTITY) -> Boolean)) {
        when (loadState().first()) {
            is DataState.Fixed -> checkContent(needRefresh)
            is DataState.Loading -> Unit
            is DataState.Error -> fetchNewContent()
        }
    }

    private suspend fun checkContent(needRefresh: ((entity: ENTITY) -> Boolean)) {
        val entity = loadEntity()
        if (entity == null || needRefresh(entity)) {
            fetchNewContent()
        }
    }

    private suspend fun fetchNewContent() {
        val stateIds = listOf(stateId) + additionalStateIds
        try {
            stateIds.map { saveState(it, DataState.Loading) }
            val fetchedEntities = fetchOrigin()
            saveEntities(fetchedEntities)
            stateIds.map { saveState(it, DataState.Fixed) }
        } catch (exception: Exception) {
            stateIds.map { saveState(it, DataState.Error(exception)) }
        }
    }

}
