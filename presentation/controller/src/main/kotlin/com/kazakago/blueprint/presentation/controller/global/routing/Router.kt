package com.kazakago.blueprint.presentation.controller.global.routing

import androidx.compose.runtime.Composable
import com.kazakago.blueprint.presentation.controller.hierarchy.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun Router() {
    DestinationsNavHost(NavGraphs.root)
}
