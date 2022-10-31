package com.kazakago.blueprint.presentation.global.util

import androidx.compose.runtime.*
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
    fetch: suspend () -> T,
    refresh: suspend () -> T,
): QueryResult<T> {
    val scope: CoroutineScope = rememberCoroutineScope()
    var data: T? by remember(key) { mutableStateOf(null) }
    var loading: Boolean by remember(key) { mutableStateOf(false) }
    var error: Throwable? by remember(key) { mutableStateOf(null) }
    val fetchBlock: () -> Unit = {
        scope.launch {
            if (loading) return@launch
            loading = true
            error = null
            runCatching {
                data = fetch()
            }.onFailure {
                error = it
            }
            loading = false
        }
    }
    val refreshBlock: () -> Unit = {
        scope.launch {
            if (loading) return@launch
            loading = true
            error = null
            runCatching {
                data = refresh()
            }.onFailure {
                error = it
            }
            loading = false
        }
    }
    LaunchedEffect(key) {
        fetchBlock()
    }
    return remember(key, data, loading, error) {
        QueryResult(
            data = data,
            loading = loading,
            error = error,
            refresh = refreshBlock,
        )
    }
}

@Composable
fun <T> produceQuery(
    key: Any?,
    flow: () -> Flow<T>,
    fetch: suspend () -> Unit,
    refresh: suspend () -> Unit,
): QueryResult<T> {
    val scope: CoroutineScope = rememberCoroutineScope()
    val data: T? by flow().collectAsState(initial = null)
    var loading: Boolean by remember(key) { mutableStateOf(false) }
    var error: Throwable? by remember(key) { mutableStateOf(null) }
    val fetchBlock: () -> Unit = {
        scope.launch {
            if (loading) return@launch
            loading = true
            error = null
            runCatching {
                fetch()
            }.onFailure {
                error = it
            }
            loading = false
        }
    }
    val refreshBlock: () -> Unit = {
        scope.launch {
            if (loading) return@launch
            loading = true
            error = null
            runCatching {
                refresh()
            }.onFailure {
                error = it
            }
            loading = false
        }
    }
    LaunchedEffect(key) {
        fetchBlock()
    }
    return remember(key, data, loading, error) {
        QueryResult(
            data = data,
            loading = loading,
            error = error,
            refresh = refreshBlock,
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
    fetch: suspend () -> Unit,
    refresh: suspend () -> Unit,
    next: suspend () -> Unit,
): PagingQueryResult<T> {
    val scope: CoroutineScope = rememberCoroutineScope()
    val data: T? by flow().collectAsState(initial = null)
    var loading: Boolean by remember(key) { mutableStateOf(false) }
    var error: Throwable? by remember(key) { mutableStateOf(null) }
    var loadingNext: Boolean by remember(key) { mutableStateOf(false) }
    var errorNext: Throwable? by remember(key) { mutableStateOf(null) }
    val fetchBlock: () -> Unit = {
        scope.launch {
            if (loading || loadingNext) return@launch
            loading = true
            loadingNext = false
            error = null
            errorNext = null
            runCatching {
                fetch()
            }.onFailure {
                error = it
            }
            loading = false
        }
    }
    val refreshBlock: () -> Unit = {
        scope.launch {
            if (loading || loadingNext) return@launch
            loading = true
            loadingNext = false
            error = null
            errorNext = null
            runCatching {
                refresh()
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
        fetchBlock()
    }
    return remember(key, data, loading, error, loadingNext, errorNext) {
        PagingQueryResult(
            data = data,
            loading = loading,
            error = error,
            refresh = refreshBlock,
            next = nextBlock,
            loadingNext = loadingNext,
            errorNext = errorNext,
        )
    }
}
