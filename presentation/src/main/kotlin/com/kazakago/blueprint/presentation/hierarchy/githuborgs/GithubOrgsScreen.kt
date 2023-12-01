package com.kazakago.blueprint.presentation.hierarchy.githuborgs

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgId
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.presentation.R
import com.kazakago.blueprint.presentation.global.theme.AppTheme
import com.kazakago.blueprint.presentation.global.ui.DefaultInfiniteLayout
import com.kazakago.blueprint.presentation.hierarchy.destinations.AboutScreenDestination
import com.kazakago.blueprint.presentation.hierarchy.destinations.GithubReposScreenDestination
import com.kazakago.swr.compose.state.SWRInfiniteState
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
    state: SWRInfiniteState<GithubOrgKey, List<GithubOrg>> = useGithubOrgs(),
) {
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
    ) { paddingValues ->
        DefaultInfiniteLayout(
            state = state,
            modifier = modifier.padding(paddingValues),
        ) { githubOrg ->
            GithubOrgRow(
                githubOrg = githubOrg,
                onClickItem = { navigator.navigate(GithubReposScreenDestination(it.name)) },
            )
        }
    }
}

@Preview
@Composable
fun GithubOrgsScreenPreview() {
    AppTheme {
        GithubOrgsScreen(
            navigator = EmptyDestinationsNavigator,
            state = SWRInfiniteState.empty(
                data = listOf(
                    listOf(
                        GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                        GithubOrg(id = GithubOrgId(2), name = GithubOrgName("apple"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                        GithubOrg(id = GithubOrgId(3), name = GithubOrgName("google"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
                    ),
                ),
            ),
        )
    }
}
