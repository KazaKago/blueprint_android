package com.kazakago.blueprint.presentation.controller.hierarchy.github

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kazakago.blueprint.presentation.controller.hierarchy.destinations.AboutControllerDestination
import com.kazakago.blueprint.presentation.controller.hierarchy.destinations.GithubReposControllerDestination
import com.kazakago.blueprint.presentation.ui.global.theme.AppTheme
import com.kazakago.blueprint.presentation.ui.hierarchy.github.GithubOrgsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
@OptIn(ExperimentalLifecycleComposeApi::class)
fun GithubOrgsController(navigator: DestinationsNavigator) {
    val viewModel: GithubOrgsViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isRefreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    AppTheme {
        GithubOrgsScreen(
            uiState = uiState,
            isRefreshing = isRefreshing,
            onClickItem = { navigator.navigate(GithubReposControllerDestination(it.name)) },
            onClickAbout = { navigator.navigate(AboutControllerDestination()) },
            onBottomReached = viewModel::requestAddition,
            onRefresh = viewModel::refresh,
            onRetry = viewModel::retry,
            onRetryAdditional = viewModel::retryAddition,
        )
    }
}
