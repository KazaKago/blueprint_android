package com.kazakago.blueprint.presentation.view.hierarchy.github

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepoId
import com.kazakago.blueprint.presentation.view.global.theme.AppTheme
import com.kazakago.blueprint.presentation.view.global.util.OnBottomReached
import com.kazakago.blueprint.presentation.view.global.view.ErrorContent
import com.kazakago.blueprint.presentation.view.global.view.ErrorRow
import com.kazakago.blueprint.presentation.view.global.view.LoadingContent
import com.kazakago.blueprint.presentation.view.global.view.LoadingRow
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.github.GithubReposUiState
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
            TopAppBar(
                title = { Text(uiState.githubOrgName.value) },
                navigationIcon = {
                    IconButton(onClick = onClickBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            ListContent(
                githubRepos = uiState.githubReposOrEmpty(),
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
    githubRepos: List<GithubRepo>,
    isRefreshing: Boolean,
    onClickItem: (githubRepo: GithubRepo) -> Unit,
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
            items(githubRepos) { githubRepo ->
                GithubRepoRow(githubRepo = githubRepo, onClickItem = onClickItem)
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
fun PreviewGithubReposScreenOnLoading() {
    AppTheme {
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
    AppTheme {
        GithubReposScreen(
            uiState = GithubReposUiState.Completed(
                githubOrgName = GithubOrgName("kazakago"),
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
    AppTheme {
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
    AppTheme {
        GithubReposScreen(
            uiState = GithubReposUiState.AdditionalLoading(
                githubOrgName = GithubOrgName("kazakago"),
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
    AppTheme {
        GithubReposScreen(
            uiState = GithubReposUiState.AdditionalError(
                githubOrgName = GithubOrgName("kazakago"),
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
