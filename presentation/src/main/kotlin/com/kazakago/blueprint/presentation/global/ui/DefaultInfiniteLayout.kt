package com.kazakago.blueprint.presentation.global.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.kazakago.blueprint.presentation.global.theme.AppTheme
import com.kazakago.blueprint.presentation.global.util.OnBottomReached
import com.kazakago.swr.compose.state.SWRInfiniteState
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun <KEY, DATA> DefaultInfiniteLayout(
    state: SWRInfiniteState<KEY, List<DATA>>,
    modifier: Modifier = Modifier,
    content: @Composable LazyItemScope.(data: DATA) -> Unit,
) {
    val (data, error, _, isValidating, mutate, size, setSize) = state
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(Unit) {
            mutate()
            pullToRefreshState.endRefresh()
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection),
    ) {
        if (data.isNullOrEmpty()) {
            if (error == null || isValidating) {
                LoadingContent()
            } else {
                ErrorContent(
                    error = error,
                    onRetry = { scope.launch { runCatching { mutate() } } },
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = listState,
            ) {
                val flattenData = data.flatMap { it ?: listOf(null) }
                items(flattenData.size) { index ->
                    val singleData = flattenData[index]
                    if (singleData == null) {
                        if (error == null || isValidating) {
                            LoadingRow()
                        } else {
                            ErrorRow(
                                error = error,
                                onRetry = { scope.launch { mutate() } },
                            )
                        }
                    } else {
                        content(singleData)
                    }
                }
            }
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = pullToRefreshState,
            )
            listState.OnBottomReached(
                loadMore = { setSize(size + 1) },
            )
        }
    }
}

@Preview
@Composable
fun DefaultInfiniteLayoutWithDataPreview() {
    AppTheme {
        Scaffold { paddingValues ->
            DefaultInfiniteLayout(
                modifier = Modifier.padding(paddingValues),
                state = SWRInfiniteState.empty<String, List<String>>(
                    data = listOf(listOf("hogehoge1", "hogehoge2", "hogehoge3"), listOf("hogehoge4", "hogehoge5", "hogehoge6")),
                    size = 2,
                ),
            ) { data ->
                Text(text = data)
            }
        }
    }
}

@Preview
@Composable
fun DefaultInfiniteLayoutWithLoadingPreview() {
    AppTheme {
        Scaffold { paddingValues ->
            DefaultInfiniteLayout(
                modifier = Modifier.padding(paddingValues),
                state = SWRInfiniteState.empty<String, List<String>>(),
            ) { data ->
                Text(text = data)
            }
        }
    }
}

@Preview
@Composable
fun DefaultInfiniteLayoutWithErrorPreview() {
    AppTheme {
        Scaffold { paddingValues ->
            DefaultInfiniteLayout(
                modifier = Modifier.padding(paddingValues),
                state = SWRInfiniteState.empty<String, List<String>>(
                    error = IllegalArgumentException(),
                ),
            ) { data ->
                Text(text = data)
            }
        }
    }
}

@Preview
@Composable
fun DefaultInfiniteLayoutWithDataAndValidatingPreview() {
    AppTheme {
        Scaffold { paddingValues ->
            DefaultInfiniteLayout(
                modifier = Modifier.padding(paddingValues),
                state = SWRInfiniteState.empty<String, List<String>>(
                    data = listOf(listOf("hogehoge1", "hogehoge2", "hogehoge3"), listOf("hogehoge4", "hogehoge5", "hogehoge6"), null),
                    size = 3,
                    isValidating = true,
                ),
            ) { data ->
                Text(text = data)
            }
        }
    }
}

@Preview
@Composable
fun DefaultInfiniteLayoutWithDataAndErrorPreview() {
    AppTheme {
        Scaffold { paddingValues ->
            DefaultInfiniteLayout(
                modifier = Modifier.padding(paddingValues),
                state = SWRInfiniteState.empty<String, List<String>>(
                    data = listOf(listOf("hogehoge1", "hogehoge2", "hogehoge3"), listOf("hogehoge4", "hogehoge5", "hogehoge6"), null),
                    size = 3,
                    error = IllegalArgumentException(),
                ),
            ) { data ->
                Text(text = data)
            }
        }
    }
}
