package com.kazakago.blueprint.presentation.hierarchy.githubrepos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.kazakago.blueprint.domain.model.github.*
import com.kazakago.blueprint.presentation.global.theme.AppTheme
import com.kazakago.blueprint.presentation.global.ui.*
import com.kazakago.blueprint.presentation.global.util.OnBottomReached
import com.kazakago.blueprint.presentation.global.util.PagingQueryResult
import com.kazakago.blueprint.presentation.global.util.useActionView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import java.net.URL

@Destination
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GithubReposScreen(
    name: GithubOrgName,
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier,
    query: PagingQueryResult<GithubOrgAndRepos> = queryGithubRepos(name),
) {
    val actionView = useActionView()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = { Text(query.data?.githubOrg?.name?.value ?: name.value) },
                navigationIcon = { BackIconButton(onClick = navigator::popBackStack) },
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
                GithubReposContent(
                    githubOrgAndRepos = query.data,
                    loading = query.loading,
                    onNext = query.next,
                    loadingNext = query.loadingNext,
                    errorNext = query.errorNext,
                    onClickItem = { actionView(it.url) },
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
private fun GithubReposContent(
    githubOrgAndRepos: GithubOrgAndRepos,
    loading: Boolean,
    loadingNext: Boolean,
    errorNext: Throwable?,
    onClickItem: (GithubRepo) -> Unit,
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
    Box(
        modifier = modifier.nestedScroll(pullToRefreshState.nestedScrollConnection),
    ) {
        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            item {
                GithubRepoTopRow(githubOrgAndRepos.githubOrg)
            }
            items(githubOrgAndRepos.githubRepos) { githubRepo ->
                GithubRepoRow(githubRepo, onClickItem)
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
fun GithubReposScreenPreview() {
    AppTheme {
        GithubReposScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                data = GithubOrgAndRepos(
                    githubOrg = GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    githubRepos = listOf(
                        GithubRepo(id = GithubRepoId(1), name = "cueue_server", url = URL("https://github.com/KazaKago/cueue_server")),
                        GithubRepo(id = GithubRepoId(2), name = "cueue_flutter", url = URL("https://github.com/KazaKago/cueue_flutter")),
                        GithubRepo(id = GithubRepoId(3), name = "cueue_page", url = URL("https://github.com/KazaKago/cueue_page")),
                    ),
                ),
            ),
            name = GithubOrgName("kazakago"),
        )
    }
}

@Preview
@Composable
fun GithubReposScreenWithLoadingPreview() {
    AppTheme {
        GithubReposScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                loading = true,
            ),
            name = GithubOrgName("kazakago"),
        )
    }
}

@Preview
@Composable
fun GithubReposScreenWithRefreshingPreview() {
    AppTheme {
        GithubReposScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                data = GithubOrgAndRepos(
                    githubOrg = GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    githubRepos = listOf(
                        GithubRepo(id = GithubRepoId(1), name = "cueue_server", url = URL("https://github.com/KazaKago/cueue_server")),
                        GithubRepo(id = GithubRepoId(2), name = "cueue_flutter", url = URL("https://github.com/KazaKago/cueue_flutter")),
                        GithubRepo(id = GithubRepoId(3), name = "cueue_page", url = URL("https://github.com/KazaKago/cueue_page")),
                    ),
                ),
                loading = true,
            ),
            name = GithubOrgName("kazakago"),
        )
    }
}

@Preview
@Composable
fun GithubReposScreenWithErrorPreview() {
    AppTheme {
        GithubReposScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                error = IllegalAccessException("hogehogepiyopiyohogehogepiyopiyohogehogepiyopiyo"),
            ),
            name = GithubOrgName("kazakago"),
        )
    }
}

@Preview
@Composable
fun GithubReposScreenWithLoadingNextPreview() {
    AppTheme {
        GithubReposScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                data = GithubOrgAndRepos(
                    githubOrg = GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    githubRepos = listOf(
                        GithubRepo(id = GithubRepoId(1), name = "cueue_server", url = URL("https://github.com/KazaKago/cueue_server")),
                        GithubRepo(id = GithubRepoId(2), name = "cueue_flutter", url = URL("https://github.com/KazaKago/cueue_flutter")),
                        GithubRepo(id = GithubRepoId(3), name = "cueue_page", url = URL("https://github.com/KazaKago/cueue_page")),
                    ),
                ),
                loadingNext = true,
            ),
            name = GithubOrgName("kazakago"),
        )
    }
}

@Preview
@Composable
fun GithubReposScreenWithErrorNextPreview() {
    AppTheme {
        GithubReposScreen(
            navigator = EmptyDestinationsNavigator,
            query = PagingQueryResult.forPreview(
                data = GithubOrgAndRepos(
                    githubOrg = GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    githubRepos = listOf(
                        GithubRepo(id = GithubRepoId(1), name = "cueue_server", url = URL("https://github.com/KazaKago/cueue_server")),
                        GithubRepo(id = GithubRepoId(2), name = "cueue_flutter", url = URL("https://github.com/KazaKago/cueue_flutter")),
                        GithubRepo(id = GithubRepoId(3), name = "cueue_page", url = URL("https://github.com/KazaKago/cueue_page")),
                    ),
                ),
                errorNext = IllegalAccessException("hogehogepiyopiyohogehogepiyopiyohogehogepiyopiyo"),
            ),
            name = GithubOrgName("kazakago"),
        )
    }
}
