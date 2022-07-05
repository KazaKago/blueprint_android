package com.kazakago.blueprint.presentation.controller.global.routing

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import com.kazakago.blueprint.presentation.controller.hierarchy.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
@OptIn(ExperimentalLifecycleComposeApi::class)
fun Router() {
    DestinationsNavHost(NavGraphs.root)
}
