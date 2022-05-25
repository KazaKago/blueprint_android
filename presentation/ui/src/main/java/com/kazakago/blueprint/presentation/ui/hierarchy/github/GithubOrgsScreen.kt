package com.kazakago.blueprint.presentation.ui.hierarchy.github

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgId
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.presentation.ui.R
import com.kazakago.blueprint.presentation.ui.global.theme.PreviewTheme
import com.kazakago.blueprint.presentation.ui.global.ui.ErrorContent
import com.kazakago.blueprint.presentation.ui.global.ui.ErrorRow
import com.kazakago.blueprint.presentation.ui.global.ui.LoadingContent
import com.kazakago.blueprint.presentation.ui.global.ui.LoadingRow
import com.kazakago.blueprint.presentation.ui.global.util.OnBottomReached
import com.kazakago.blueprint.presentation.uistate.hierarchy.github.GithubOrgsUiState
import java.net.URL

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
            SmallTopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(onClick = onClickAbout) {
                        Icon(Icons.Outlined.Info, contentDescription = null)
                    }
                },
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
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
                    items(uiState.getGithubOrgsOrEmpty()) { githubOrg ->
                        GithubOrgRow(githubOrg = githubOrg, onClickItem = onClickItem)
                    }
                    item {
                        uiState.MayLayout(
                            onAdditionalLoading = { LoadingRow() },
                            onAdditionalError = { ErrorRow(it.error, onRetryAdditional) },
                        )
                    }
                }
                listState.OnBottomReached {
                    onBottomReached()
                }
            }
            uiState.MayLayout(
                onLoading = { LoadingContent() },
                onError = { ErrorContent(error = it.error, onRetry = onRetry) },
            )
        }
    }
}

@Preview
@Composable
fun PreviewGithubOrgsScreenOnLoading() {
    PreviewTheme {
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
    PreviewTheme {
        GithubOrgsScreen(
            uiState = GithubOrgsUiState.Completed(
                githubOrgs = listOf(
                    GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
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
    PreviewTheme {
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
    PreviewTheme {
        GithubOrgsScreen(
            uiState = GithubOrgsUiState.AdditionalLoading(
                githubOrgs = listOf(
                    GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
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
    PreviewTheme {
        GithubOrgsScreen(
            uiState = GithubOrgsUiState.AdditionalError(
                githubOrgs = listOf(
                    GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
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