package com.kazakago.blueprint.ui.global.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.kazakago.blueprint.ui.global.hooks.useErrorMessage
import com.kazakago.blueprint.ui.global.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun ErrorSnackbar(
    state: SnackbarHostState,
    error: Throwable,
) {
    val scope = rememberCoroutineScope()
    val useErrorMessage = useErrorMessage()
    LaunchedEffect(error) {
        scope.launch {
            val message = useErrorMessage(error)
            if (message != null) state.showSnackbar(message)
        }
    }
}

@Preview
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ErrorSnackbarPreview() {
    AppTheme {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            content = {},
        )
        ErrorSnackbar(
            state = snackbarHostState,
            error = IllegalArgumentException(),
        )
    }
}
