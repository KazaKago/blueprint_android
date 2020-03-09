package com.kazakago.cleanarchitecture.data.repository.global.dispatcher

import com.kazakago.cleanarchitecture.data.memory.global.DataState
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.global.state.StateContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class CacheFlowDispatcher<out T>(
    private val loadState: (() -> Flow<DataState>),
    private val saveState: (suspend (state: DataState) -> Unit),
    private val loadContent: (suspend () -> T?),
    private val saveContent: (suspend (content: T) -> Unit),
    private val fetchContent: (suspend () -> T)
) {

    fun subscribe(needRefresh: (suspend (content: T) -> Boolean)): Flow<State<T>> {
        return loadState()
            .onStart {
                CoroutineScope(Dispatchers.IO).launch { checkState(needRefresh) }
            }
            .map {
                mapState(it, needRefresh)
            }
    }

    suspend fun request(needRefresh: (suspend (content: T) -> Boolean) = { true }) {
        checkState(needRefresh)
    }

    private suspend fun mapState(dataState: DataState, needRefresh: (suspend (content: T) -> Boolean)): State<T> {
        val loadedContent = loadContent()
        val stateContent = if (loadedContent == null || needRefresh(loadedContent)) {
            StateContent.NotExist<T>()
        } else {
            StateContent.Exist(loadedContent)
        }
        return when (dataState) {
            is DataState.Fixed -> State.Fixed(stateContent)
            is DataState.Loading -> State.Loading(stateContent)
            is DataState.Error -> State.Error(stateContent, dataState.exception)
        }
    }

    private suspend fun checkState(needRefresh: (suspend (content: T) -> Boolean)) {
        when (loadState().first()) {
            is DataState.Fixed -> checkContent(needRefresh)
            is DataState.Loading -> Unit
            is DataState.Error -> fetchNewContent()
        }
    }

    private suspend fun checkContent(needRefresh: (suspend (content: T) -> Boolean)) {
        val loadedContent = loadContent()
        if (loadedContent == null || needRefresh(loadedContent)) {
            fetchNewContent()
        }
    }

    private suspend fun fetchNewContent() {
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
