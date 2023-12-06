package com.kazakago.blueprint.ui.global.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.kazakago.blueprint.ui.global.theme.AppTheme
import com.kazakago.swr.compose.state.SWRState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <KEY, DATA> DefaultLayout(
    state: SWRState<KEY, DATA>,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.(data: DATA) -> Unit,
) {
    val (data, error, isLoading, isValidating, mutate) = state
    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(Unit) {
            state.mutate()
            pullToRefreshState.endRefresh()
        }
    }
    val scope = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(pullToRefreshState.nestedScrollConnection),
    ) {
        if (data == null) {
            if (error == null || isValidating) {
                LoadingContent()
            } else {
                ErrorContent(
                    error = error,
                    onRetry = {
                        scope.launch { runCatching { mutate() } }
                    },
                )
            }
        } else {
            content(data)
            if (error != null) {
                ErrorSnackbar(
                    state = snackbarHostState,
                    error = error,
                )
            }
            if (isLoading || isValidating) {
                LinearProgressIndicator(Modifier.fillMaxWidth())
            }
        }
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState,
        )
    }
}

@Preview
@Composable
fun DefaultLayoutWithDataPreview() {
    AppTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { paddingValues ->
            DefaultLayout(
                modifier = Modifier.padding(paddingValues),
                state = SWRState.empty<String, String>(
                    data = "hogehoge",
                ),
                snackbarHostState = snackbarHostState,
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = it,
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultLayoutWithLoadingPreview() {
    AppTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { paddingValues ->
            DefaultLayout(
                modifier = Modifier.padding(paddingValues),
                state = SWRState.empty<String, String>(),
                snackbarHostState = snackbarHostState,
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = it,
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultLayoutWithErrorPreview() {
    AppTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { paddingValues ->
            DefaultLayout(
                modifier = Modifier.padding(paddingValues),
                state = SWRState.empty<String, String>(
                    error = IllegalArgumentException(),
                ),
                snackbarHostState = snackbarHostState,
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = it,
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultLayoutWithDataAndValidatingPreview() {
    AppTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { paddingValues ->
            DefaultLayout(
                modifier = Modifier.padding(paddingValues),
                state = SWRState.empty<String, String>(
                    data = "hogehoge",
                    isValidating = true,
                ),
                snackbarHostState = snackbarHostState,
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = it,
                )
            }
        }
    }
}

@Preview
@Composable
fun DefaultLayoutWithDataAndErrorPreview() {
    AppTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { paddingValues ->
            DefaultLayout(
                modifier = Modifier.padding(paddingValues),
                state = SWRState.empty<String, String>(
                    data = "hogehoge",
                    error = IllegalArgumentException(),
                ),
                snackbarHostState = snackbarHostState,
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = it,
                )
            }
        }
    }
}
