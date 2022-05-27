package com.kazakago.blueprint.presentation.controller.hierarchy.github

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kazakago.blueprint.presentation.ui.global.theme.AppTheme
import com.kazakago.blueprint.presentation.ui.hierarchy.github.GithubReposScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.net.URL

@Destination(navArgsDelegate = GithubReposNagArgs::class)
@Composable
fun GithubReposController(navigator: DestinationsNavigator) {
    val viewModel: GithubReposViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    val context = LocalContext.current
    AppTheme {
        GithubReposScreen(
            uiState = uiState,
            isRefreshing = isRefreshing,
            onClickBack = navigator::popBackStack,
            onClickItem = { actionView(context, it.url) },
            onBottomReached = viewModel::requestAddition,
            onRefresh = viewModel::refresh,
            onRetry = viewModel::retry,
            onRetryAdditional = viewModel::retryAddition,
        )
    }
}

private fun actionView(context: Context, url: URL) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
    context.startActivity(intent)
}
