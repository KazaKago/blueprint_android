package com.kazakago.blueprint.presentation.ui.hierarchy.github

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kazakago.blueprint.domain.model.hierarchy.github.*
import com.kazakago.blueprint.presentation.ui.global.theme.PreviewTheme
import com.kazakago.blueprint.presentation.ui.global.ui.*
import com.kazakago.blueprint.presentation.ui.global.util.OnBottomReached
import com.kazakago.blueprint.presentation.uistate.hierarchy.github.GithubReposUiState
import java.net.URL

@Composable
fun GithubReposScreen(
    uiState: GithubReposUiState,
    isRefreshing: Boolean,
    onClickBack: () -> Unit,
    onClickItem: (githubRepos: GithubRepo) -> Unit,
    onBottomReached: () -> Unit,
    onRefresh: () -> Unit,
    onRetry: () -> Unit,
    onRetryAdditional: () -> Unit,
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(uiState.githubOrgName.value) },
                navigationIcon = { BackIconButton(onClick = onClickBack) },
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
                    item {
                        uiState.MayLayout(
                            onCompleted = { GithubRepoTopRow(githubOrg = it.githubOrg) },
                            onAdditionalError = { GithubRepoTopRow(githubOrg = it.githubOrg) },
                            onAdditionalLoading = { GithubRepoTopRow(githubOrg = it.githubOrg) }
                        )
                    }
                    items(uiState.getGithubReposOrEmpty()) { githubRepo ->
                        GithubRepoRow(githubRepo = githubRepo, onClickItem = onClickItem)
                    }
                    item {
                        uiState.MayLayout(
                            onAdditionalError = { ErrorRow(it.error, onRetryAdditional) },
                            onAdditionalLoading = { LoadingRow() },
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
fun PreviewGithubReposScreenOnLoading() {
    PreviewTheme {
        GithubReposScreen(
            uiState = GithubReposUiState.Loading(
                githubOrgName = GithubOrgName("kazakago"),
            ),
            isRefreshing = false,
            onClickBack = {},
            onClickItem = {},
            onBottomReached = {},
            onRefresh = {},
            onRetry = {},
            onRetryAdditional = {},
        )
    }
}

@Preview
@Composable
fun PreviewGithubReposScreenOnCompleted() {
    PreviewTheme {
        GithubReposScreen(
            uiState = GithubReposUiState.Completed(
                githubOrg = GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                githubRepos = listOf(
                    GithubRepo(id = GithubRepoId(1), name = "cueue_server", url = URL("https://github.com/KazaKago/cueue_server")),
                    GithubRepo(id = GithubRepoId(2), name = "cueue_flutter", url = URL("https://github.com/KazaKago/cueue_flutter")),
                    GithubRepo(id = GithubRepoId(3), name = "cueue_page", url = URL("https://github.com/KazaKago/cueue_page")),
                ),
            ),
            isRefreshing = false,
            onClickBack = {},
            onClickItem = {},
            onBottomReached = {},
            onRefresh = {},
            onRetry = {},
            onRetryAdditional = {},
        )
    }
}

@Preview
@Composable
fun PreviewGithubReposScreenOnError() {
    PreviewTheme {
        GithubReposScreen(
            uiState = GithubReposUiState.Error(
                githubOrgName = GithubOrgName("kazakago"),
                error = IllegalAccessException("hogehogepiyopiyohogehogepiyopiyohogehogepiyopiyo"),
            ),
            isRefreshing = false,
            onClickBack = {},
            onClickItem = {},
            onBottomReached = {},
            onRefresh = {},
            onRetry = {},
            onRetryAdditional = {},
        )
    }
}

@Preview
@Composable
fun PreviewGithubReposScreenOnAdditionalLoading() {
    PreviewTheme {
        GithubReposScreen(
            uiState = GithubReposUiState.AdditionalLoading(
                githubOrg = GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                githubRepos = listOf(
                    GithubRepo(id = GithubRepoId(1), name = "cueue_server", url = URL("https://github.com/KazaKago/cueue_server")),
                    GithubRepo(id = GithubRepoId(2), name = "cueue_flutter", url = URL("https://github.com/KazaKago/cueue_flutter")),
                    GithubRepo(id = GithubRepoId(3), name = "cueue_page", url = URL("https://github.com/KazaKago/cueue_page")),
                ),
            ),
            isRefreshing = false,
            onClickBack = {},
            onClickItem = {},
            onBottomReached = {},
            onRefresh = {},
            onRetry = {},
            onRetryAdditional = {},
        )
    }
}

@Preview
@Composable
fun PreviewGithubReposScreenOnAdditionalError() {
    PreviewTheme {
        GithubReposScreen(
            uiState = GithubReposUiState.AdditionalError(
                githubOrg = GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                githubRepos = listOf(
                    GithubRepo(id = GithubRepoId(1), name = "cueue_server", url = URL("https://github.com/KazaKago/cueue_server")),
                    GithubRepo(id = GithubRepoId(2), name = "cueue_flutter", url = URL("https://github.com/KazaKago/cueue_flutter")),
                    GithubRepo(id = GithubRepoId(3), name = "cueue_page", url = URL("https://github.com/KazaKago/cueue_page")),
                ),
                error = IllegalAccessException("hogehogepiyopiyohogehogepiyopiyohogehogepiyopiyo"),
            ),
            isRefreshing = false,
            onClickBack = {},
            onClickItem = {},
            onBottomReached = {},
            onRefresh = {},
            onRetry = {},
            onRetryAdditional = {},
        )
    }
}
