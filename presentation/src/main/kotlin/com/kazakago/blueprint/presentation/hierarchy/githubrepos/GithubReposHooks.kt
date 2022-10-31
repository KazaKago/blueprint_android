package com.kazakago.blueprint.presentation.hierarchy.githubrepos

import androidx.compose.runtime.Composable
import com.kazakago.blueprint.domain.model.github.GithubOrgAndRepos
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.presentation.global.util.LocalGithubRepository
import com.kazakago.blueprint.presentation.global.util.PagingQueryResult
import com.kazakago.blueprint.presentation.global.util.producePagingQuery
import kotlinx.coroutines.flow.combine

@Composable
fun queryGithubRepos(githubOrgName: GithubOrgName): PagingQueryResult<GithubOrgAndRepos> {
    val repository = LocalGithubRepository.current
    return producePagingQuery(
        key = githubOrgName,
        flow = {
            repository.flowOrg(githubOrgName = githubOrgName)
                .combine(repository.flowRepos(githubOrgName = githubOrgName)) { orgs, repos ->
                    GithubOrgAndRepos(orgs, repos)
                }
        },
        fetch = { repository.requestRepos(force = it, githubOrgName = githubOrgName) },
        next = { repository.requestReposNext(githubOrgName = githubOrgName) },
    )
}
