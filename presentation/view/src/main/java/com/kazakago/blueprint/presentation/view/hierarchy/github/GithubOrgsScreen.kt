package com.kazakago.blueprint.presentation.view.hierarchy.github

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgId
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.global.theme.AppTheme
import com.kazakago.blueprint.presentation.view.global.util.OnBottomReached
import com.kazakago.blueprint.presentation.view.global.view.ErrorContent
import com.kazakago.blueprint.presentation.view.global.view.ErrorRow
import com.kazakago.blueprint.presentation.view.global.view.LoadingContent
import com.kazakago.blueprint.presentation.view.global.view.LoadingRow
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.github.GithubOrgsUiState

@Composable
fun GithubOrgsScreen(
    uiState: GithubOrgsUiState,
    isRefreshing: Boolean,
    onClickItem: (githubOrg: GithubOrg) -> Unit,
    onClickAbout: () -> Unit,
    onBottomReached: () -> Unit,
    onRefresh: () -> Unit,
    onRetry: () -> Unit,
    onRetryAdditional: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(onClick = onClickAbout) {
                        Icon(Icons.Outlined.Info, contentDescription = stringResource(id = R.string.action_about))
                    }
                }
            )
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ListContent(
                githubOrgs = uiState.githubOrgsOrEmpty(),
                isRefreshing = isRefreshing,
                onClickItem = onClickItem,
                onBottomReached = onBottomReached,
                onRefresh = onRefresh,
            ) {
                uiState.MayLayout(
                    onAdditionalError = { ErrorRow(it.error, onRetryAdditional) },
                    onAdditionalLoading = { LoadingRow() },
                )
            }
            uiState.MayLayout(
                onLoading = { LoadingContent() },
                onError = { ErrorContent(error = it.error, onRetry = onRetry) },
            )
        }
    }
}

@Composable
private fun ListContent(
    githubOrgs: List<GithubOrg>,
    isRefreshing: Boolean,
    onClickItem: (githubOrg: GithubOrg) -> Unit,
    onBottomReached: () -> Unit,
    onRefresh: () -> Unit,
    additionalItem: @Composable () -> Unit = {},
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = onRefresh,
    ) {
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            items(githubOrgs) { githubOrg ->
                GithubOrgRow(githubOrg = githubOrg, onClickItem = onClickItem)
            }
            item {
                additionalItem()
            }
        }
        listState.OnBottomReached {
            onBottomReached()
        }
    }
}


@Preview
@Composable
fun PreviewGithubOrgsScreenOnLoading() {
    AppTheme {
        GithubOrgsScreen(
            uiState = GithubOrgsUiState.Loading,
            isRefreshing = false,
            onClickItem = {},
            onClickAbout = {},
            onBottomReached = {},
            onRefresh = {},
            onRetry = {},
            onRetryAdditional = {},
        )
    }
}

@Preview
@Composable
fun PreviewGithubOrgsScreenOnCompleted() {
    AppTheme {
        GithubOrgsScreen(
            uiState = GithubOrgsUiState.Completed(
                githubOrgs = listOf(
                    GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago")),
                    GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple")),
                    GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google")),
                ),
            ),
            isRefreshing = false,
            onClickItem = {},
            onClickAbout = {},
            onBottomReached = {},
            onRefresh = {},
            onRetry = {},
            onRetryAdditional = {},
        )
    }
}

@Preview
@Composable
fun PreviewGithubOrgsScreenOnError() {
    AppTheme {
        GithubOrgsScreen(
            uiState = GithubOrgsUiState.Error(
                error = IllegalAccessException("hogehogepiyopiyohogehogepiyopiyohogehogepiyopiyo"),
            ),
            isRefreshing = false,
            onClickItem = {},
            onClickAbout = {},
            onBottomReached = {},
            onRefresh = {},
            onRetry = {},
            onRetryAdditional = {},
        )
    }
}

@Preview
@Composable
fun PreviewGithubOrgsScreenOnAdditionalLoading() {
    AppTheme {
        GithubOrgsScreen(
            uiState = GithubOrgsUiState.AdditionalLoading(
                githubOrgs = listOf(
                    GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago")),
                    GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple")),
                    GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google")),
                ),
            ),
            isRefreshing = false,
            onClickItem = {},
            onClickAbout = {},
            onBottomReached = {},
            onRefresh = {},
            onRetry = {},
            onRetryAdditional = {},
        )
    }
}

@Preview
@Composable
fun PreviewGithubOrgsScreenOnAdditionalError() {
    AppTheme {
        GithubOrgsScreen(
            uiState = GithubOrgsUiState.AdditionalError(
                githubOrgs = listOf(
                    GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago")),
                    GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple")),
                    GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google")),
                ),
                error = IllegalAccessException("hogehogepiyopiyohogehogepiyopiyohogehogepiyopiyo"),
            ),
            isRefreshing = false,
            onClickItem = {},
            onClickAbout = {},
            onBottomReached = {},
            onRefresh = {},
            onRetry = {},
            onRetryAdditional = {},
        )
    }
}
