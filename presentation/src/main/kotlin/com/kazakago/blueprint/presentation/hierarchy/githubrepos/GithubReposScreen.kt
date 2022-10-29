package com.kazakago.blueprint.presentation.hierarchy.githubrepos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kazakago.blueprint.domain.model.github.*
import com.kazakago.blueprint.presentation.global.theme.PreviewTheme
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
    query: PagingQueryResult<GithubOrgAndRepos> = queryGithubRepos(name),
) {
    val actionView = useActionView()
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
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
fun GithubReposContent(
    githubOrgAndRepos: GithubOrgAndRepos,
    loading: Boolean,
    loadingNext: Boolean,
    errorNext: Throwable?,
    onClickItem: (GithubRepo) -> Unit,
    onNext: () -> Unit,
    onRefresh: () -> Unit,
) {
    SwipeRefresh(
        state = rememberSwipeRefreshState(loading),
        onRefresh = onRefresh,
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
        listState.OnBottomReached {
            if (errorNext == null) {
                onNext()
            }
        }
    }
}

@Preview
@Composable
fun PreviewGithubReposScreen() {
    PreviewTheme {
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
fun PreviewGithubReposScreenWithLoading() {
    PreviewTheme {
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
fun PreviewGithubReposScreenWithRefreshing() {
    PreviewTheme {
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
fun PreviewGithubReposScreenWithError() {
    PreviewTheme {
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
fun PreviewGithubReposScreenWithLoadingNext() {
    PreviewTheme {
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
fun PreviewGithubReposScreenWithErrorNext() {
    PreviewTheme {
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
