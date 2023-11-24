package com.kazakago.blueprint.presentation.hierarchy.githuborgs

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgId
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.presentation.R
import com.kazakago.blueprint.presentation.global.theme.AppTheme
import com.kazakago.blueprint.presentation.global.ui.*
import com.kazakago.blueprint.presentation.global.util.OnBottomReached
import com.kazakago.blueprint.presentation.global.util.PagingQueryResult
import com.kazakago.blueprint.presentation.hierarchy.destinations.AboutScreenDestination
import com.kazakago.blueprint.presentation.hierarchy.destinations.GithubReposScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import java.net.URL

@RootNavGraph(start = true)
@Destination
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GithubOrgsScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    query: PagingQueryResult<List<GithubOrg>> = queryGithubOrgs(),
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(id = R.string.app_name)) },
                actions = {
                    IconButton(onClick = { navigator.navigate(AboutScreenDestination()) }) {
                        Icon(Icons.Outlined.Info, contentDescription = null)
                    }
                },
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            if (query.data != null) {
                GithubOrgsContent(
                    githubOrgs = query.data,
                    loading = query.loading,
                    onNext = query.next,
                    loadingNext = query.loadingNext,
                    errorNext = query.errorNext,
                    onClickItem = { navigator.navigate(GithubReposScreenDestination(it.name)) },
                    onRefresh = query.refresh,
                )
            }
            if (query.loading && query.data == null) {
                LoadingContent()
            }
            if (query.error != null && query.data != null) {
                ErrorSnackbar(error = query.error, state = snackbarHostState)
            }
            if (query.error != null && query.data == null) {
                ErrorContent(error = query.error, onRetry = query.refresh)
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun GithubOrgsContent(
    githubOrgs: List<GithubOrg>,
    loading: Boolean,
    loadingNext: Boolean,
    errorNext: Throwable?,
    onClickItem: (GithubOrg) -> Unit,
    onNext: () -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullToRefreshState = rememberPullToRefreshState()
    if (pullToRefreshState.isRefreshing) {
        LaunchedEffect(Unit) {
            pullToRefreshState.startRefresh()
            onRefresh()
        }
    }
    if (!loading) {
        pullToRefreshState.endRefresh()
    }
    val listState = rememberLazyListState()
    Box(
        modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            items(githubOrgs) { githubOrg ->
                GithubOrgRow(githubOrg, onClickItem)
            }
            item {
                if (errorNext != null) {
                    ErrorRow(errorNext, onNext)
                }
                if (loadingNext) {
                    LoadingRow()
                }
            }
        }
        listState.OnBottomReached(loadMore = onNext)
        PullToRefreshContainer(
            modifier = Modifier.align(Alignment.TopCenter),
            state = pullToRefreshState,
        )
    }
}

@Preview
@Composable
fun GithubOrgsScreenPreview() {
    AppTheme {
        GithubOrgsScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                data = listOf(
                    GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                ),
            ),
        )
    }
}

@Preview
@Composable
fun GithubOrgsScreenWithLoadingPreview() {
    AppTheme {
        GithubOrgsScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                loading = true,
            ),
        )
    }
}

@Preview
@Composable
fun GithubOrgsScreenWithRefreshingPreview() {
    AppTheme {
        GithubOrgsScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                data = listOf(
                    GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                ),
                loading = true,
            ),
        )
    }
}

@Preview
@Composable
fun GithubOrgsScreenWithErrorPreview() {
    AppTheme {
        AppTheme {
            GithubOrgsScreen(
                navigator = EmptyDestinationsNavigator,
                query = PagingQueryResult.forPreview(
                    error = IllegalAccessException("hogehogepiyopiyohogehogepiyopiyohogehogepiyopiyo"),
                ),
            )
        }
    }
}

@Preview
@Composable
fun GithubOrgsScreenWithNextLoadingPreview() {
    AppTheme {
        GithubOrgsScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                data = listOf(
                    GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                ),
                loadingNext = true,
            ),
        )
    }
}

@Preview
@Composable
fun GithubOrgsScreenWithNextErrorPreview() {
    AppTheme {
        GithubOrgsScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                data = listOf(
                    GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                ),
                errorNext = IllegalAccessException("hogehogepiyopiyohogehogepiyopiyohogehogepiyopiyo"),
            ),
        )
    }
}
