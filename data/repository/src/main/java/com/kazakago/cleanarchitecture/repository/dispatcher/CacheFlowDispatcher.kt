package com.kazakago.cleanarchitecture.repository.dispatcher

import com.kazakago.cleanarchitecture.memory.memory.DataState
import com.kazakago.cleanarchitecture.model.state.State
import com.kazakago.cleanarchitecture.model.state.StateContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class CacheFlowDispatcher<T>(
    private val loadState: (() -> Flow<DataState>),
    private val saveState: (suspend (state: DataState) -> Unit),
    private val loadContent: (suspend () -> T?),
    private val saveContent: (suspend (content: T) -> Unit),
    private val fetchContent: (suspend () -> T),
    private val isStale: (suspend (content: T) -> Boolean)
) {

    fun subscribe(): Flow<State<T>> {
        return loadState()
            .onStart {
                CoroutineScope(Dispatchers.IO).launch { checkState() }
            }
            .map {
                mapState(it)
            }
    }

    suspend fun request() {
        checkState()
    }

    private suspend fun mapState(dataState: DataState): State<T> {
        val loadedContent = loadContent()
        val stateValue = if (loadedContent == null || !isStale(loadedContent)) {
            StateContent.NotStored<T>()
        } else {
            StateContent.Stored(loadedContent)
        }
        return when (dataState) {
            is DataState.Fixed -> State.Fixed(stateValue)
            is DataState.Loading -> State.Loading(stateValue)
            is DataState.Error -> State.Error(stateValue, dataState.exception)
        }
    }

    private suspend fun checkState() {
        when (loadState().first()) {
            is DataState.Fixed -> checkContent()
            is DataState.Loading -> Unit
            is DataState.Error -> fetchNewValue()
        }
    }

    private suspend fun checkContent() {
        val savedContent = loadContent()
        if (savedContent == null || isStale(savedContent)) {
            fetchNewValue()
        }
    }

    private suspend fun fetchNewValue() {
        try {
            saveState(DataState.Loading)
            val newContent = fetchContent()
            saveContent(newContent)
            saveState(DataState.Fixed)
        } catch (exception: Exception) {
            saveState(DataState.Error(exception))
        }
    }

}
