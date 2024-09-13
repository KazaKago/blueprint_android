package com.kazakago.blueprint.ui.global.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kazakago.blueprint.ui.global.theme.AppTheme
import com.kazakago.swr.compose.state.SWRState
import kotlinx.coroutines.launch

@Composable
fun <KEY, DATA> StateLayout(
    state: SWRState<KEY, DATA>,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.(data: DATA) -> Unit,
) {
    val (data, error, _, isValidating, mutate) = state
    val scope = rememberCoroutineScope()
    Box(modifier) {
        if (data == null) {
            if (error == null || isValidating) {
                LoadingContent()
            } else {
                ErrorContent(
                    error = error,
                    onRetry = {
                        scope.launch {
                            runCatching { mutate() }
                        }
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
        }
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
            StateLayout(
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
            StateLayout(
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
            StateLayout(
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
            StateLayout(
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
            StateLayout(
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
