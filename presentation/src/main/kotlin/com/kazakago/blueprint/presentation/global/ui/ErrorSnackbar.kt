package com.kazakago.blueprint.presentation.global.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
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
