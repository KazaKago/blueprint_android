package com.kazakago.blueprint.presentation.global.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Immutable
data class MutationResult<out T>(
    val mutate: () -> Unit,
    val data: T?,
    val loading: Boolean,
    val error: Throwable?,
) {
    companion object {
        fun <T> forPreview(
            mutate: () -> Unit = {},
            data: T? = null,
            loading: Boolean = false,
            error: Throwable? = null,
        ) = MutationResult(mutate, data, loading, error)
    }
}

@Composable
fun <T> produceMutation(
    key: Any?,
    mutate: suspend () -> T,
): MutationResult<T> {
    val scope: CoroutineScope = rememberCoroutineScope()
    var data: T? by remember(key) { mutableStateOf(null) }
    var loading: Boolean by remember(key) { mutableStateOf(false) }
    var error: Throwable? by remember(key) { mutableStateOf(null) }
    val mutateBlock: () -> Unit = {
        scope.launch {
            loading = true
            error = null
            runCatching {
                data = mutate()
            }.onFailure {
                error = it
            }
            loading = false
        }
    }
    return remember(key, data, loading, error) {
        MutationResult(
            mutate = mutateBlock,
            data = data,
            loading = loading,
            error = error,
        )
    }
}

@Immutable
data class QueryResult<out T>(
    val data: T?,
    val loading: Boolean,
    val error: Throwable?,
    val refresh: () -> Unit,
) {
    companion object {
        fun <T> forPreview(
            data: T? = null,
            loading: Boolean = false,
            error: Throwable? = null,
            refresh: () -> Unit = {},
        ) = QueryResult(data, loading, error, refresh)
    }
}

@Composable
fun <T> produceQuery(
    key: Any?,
    fetch: suspend (force: Boolean) -> T,
): QueryResult<T> {
    val scope: CoroutineScope = rememberCoroutineScope()
    var data: T? by remember(key) { mutableStateOf(null) }
    var loading: Boolean by remember(key) { mutableStateOf(false) }
    var error: Throwable? by remember(key) { mutableStateOf(null) }
    val fetchBlock: (force: Boolean) -> Unit = { force ->
        scope.launch {
            if (loading) return@launch
            loading = true
            error = null
            runCatching {
                data = fetch(force)
            }.onFailure {
                error = it
            }
            loading = false
        }
    }
    LaunchedEffect(key) {
        fetchBlock(false)
    }
    return remember(key, data, loading, error) {
        QueryResult(
            data = data,
            loading = loading,
            error = error,
            refresh = { fetchBlock(true) },
        )
    }
}

@Composable
fun <T> produceQuery(
    key: Any?,
    flow: () -> Flow<T>,
    fetch: suspend (force: Boolean) -> Unit,
): QueryResult<T> {
    val scope: CoroutineScope = rememberCoroutineScope()
    val data: T? by flow().collectAsStateWithLifecycle(initialValue = null)
    var loading: Boolean by remember(key) { mutableStateOf(false) }
    var error: Throwable? by remember(key) { mutableStateOf(null) }
    val fetchBlock: (force: Boolean) -> Unit = { force ->
        scope.launch {
            if (loading) return@launch
            loading = true
            error = null
            runCatching {
                fetch(force)
            }.onFailure {
                error = it
            }
            loading = false
        }
    }
    LaunchedEffect(key) {
        fetchBlock(false)
    }
    return remember(key, data, loading, error) {
        QueryResult(
            data = data,
            loading = loading,
            error = error,
            refresh = { fetchBlock(true) },
        )
    }
}

@Immutable
data class PagingQueryResult<out T>(
    val data: T?,
    val loading: Boolean,
    val error: Throwable?,
    val refresh: () -> Unit,
    val next: () -> Unit,
    val loadingNext: Boolean,
    val errorNext: Throwable?,
) {
    companion object {
        fun <T> forPreview(
            data: T? = null,
            loading: Boolean = false,
            error: Throwable? = null,
            refresh: () -> Unit = {},
            next: () -> Unit = {},
            loadingNext: Boolean = false,
            errorNext: Throwable? = null,
        ) = PagingQueryResult(data, loading, error, refresh, next, loadingNext, errorNext)
    }
}

@Composable
fun <T> producePagingQuery(
    key: Any?,
    flow: () -> Flow<T>,
    fetch: suspend (force: Boolean) -> Unit,
    next: suspend () -> Unit,
): PagingQueryResult<T> {
    val scope: CoroutineScope = rememberCoroutineScope()
    val data: T? by flow().collectAsStateWithLifecycle(initialValue = null)
    var loading: Boolean by remember(key) { mutableStateOf(false) }
    var error: Throwable? by remember(key) { mutableStateOf(null) }
    var loadingNext: Boolean by remember(key) { mutableStateOf(false) }
    var errorNext: Throwable? by remember(key) { mutableStateOf(null) }
    val fetchBlock: (force: Boolean) -> Unit = { force ->
        scope.launch {
            if (loading || loadingNext) return@launch
            loading = true
            loadingNext = false
            error = null
            errorNext = null
            runCatching {
                fetch(force)
            }.onFailure {
                error = it
            }
            loading = false
        }
    }
    val nextBlock: () -> Unit = {
        scope.launch {
            if (loading || loadingNext) return@launch
            loading = false
            loadingNext = true
            error = null
            errorNext = null
            runCatching {
                next()
            }.onFailure {
                errorNext = it
            }
            loadingNext = false
        }
    }
    LaunchedEffect(key) {
        fetchBlock(false)
    }
    return remember(key, data, loading, error, loadingNext, errorNext) {
        PagingQueryResult(
            data = data,
            loading = loading,
            error = error,
            refresh = { fetchBlock(true) },
            next = nextBlock,
            loadingNext = loadingNext,
            errorNext = errorNext,
        )
    }
}
