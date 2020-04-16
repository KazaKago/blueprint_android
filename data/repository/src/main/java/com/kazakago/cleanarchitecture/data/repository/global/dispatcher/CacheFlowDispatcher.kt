package com.kazakago.cleanarchitecture.data.repository.global.dispatcher

import com.kazakago.cleanarchitecture.data.memory.global.DataState
import com.kazakago.cleanarchitecture.data.memory.hierarchy.state.StateMemory
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.global.state.StateContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class CacheFlowDispatcher<ENTITY>(
    private val stateId: String,
    private val loadEntity: (suspend () -> ENTITY?),
    private val fetchOrigin: (suspend () -> ENTITY),
    private val saveEntity: (suspend (entity: ENTITY) -> Unit),
    private val loadState: ((stateId: String) -> Flow<DataState>) = { StateMemory[it].asFlow() },
    private val saveState: (suspend (stateId: String, state: DataState) -> Unit) = { _stateId, state -> StateMemory[_stateId].send(state) }
) {

    fun subscribe(needRefresh: ((entity: ENTITY) -> Boolean)): Flow<State<ENTITY>> {
        return loadState(stateId)
            .onStart {
                CoroutineScope(Dispatchers.IO).launch { checkState(needRefresh, false) }
            }
            .map {
                mapState(it, needRefresh)
            }
    }

    suspend fun request(fetchOnError: Boolean = true) {
        checkState({ true }, fetchOnError)
    }

    suspend fun update(newEntity: ENTITY) {
        saveEntity(newEntity)
        saveState(stateId, DataState.Fixed)
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

    private suspend fun checkState(needRefresh: ((entity: ENTITY) -> Boolean), fetchOnError: Boolean) {
        when (loadState(stateId).first()) {
            is DataState.Fixed -> checkEntity(needRefresh)
            is DataState.Loading -> Unit
            is DataState.Error -> if (fetchOnError) fetchNewEntity()
        }
    }

    private suspend fun checkEntity(needRefresh: ((entity: ENTITY) -> Boolean)) {
        val entity = loadEntity()
        if (entity == null || needRefresh(entity)) {
            fetchNewEntity()
        }
    }

    private suspend fun fetchNewEntity() {
        try {
            saveState(stateId, DataState.Loading)
            val fetchedEntity = fetchOrigin()
            saveEntity(fetchedEntity)
            saveState(stateId, DataState.Fixed)
        } catch (exception: Exception) {
            saveState(stateId, DataState.Error(exception))
        }
    }

}
