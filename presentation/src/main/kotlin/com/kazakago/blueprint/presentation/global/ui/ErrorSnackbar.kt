package com.kazakago.blueprint.presentation.global.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import com.kazakago.blueprint.presentation.global.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun ErrorSnackbar(
    state: SnackbarHostState,
    error: Throwable,
) {
    val scope = rememberCoroutineScope()
    LaunchedEffect(error) {
        scope.launch {
            state.showSnackbar(error.toString())
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
