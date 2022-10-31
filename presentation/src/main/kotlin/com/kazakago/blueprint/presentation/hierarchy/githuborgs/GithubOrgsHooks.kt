package com.kazakago.blueprint.presentation.hierarchy.githuborgs

import androidx.compose.runtime.Composable
import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.presentation.global.util.LocalGithubRepository
import com.kazakago.blueprint.presentation.global.util.PagingQueryResult
import com.kazakago.blueprint.presentation.global.util.producePagingQuery

@Composable
fun queryGithubOrgs(): PagingQueryResult<List<GithubOrg>> {
    val repository = LocalGithubRepository.current
    return producePagingQuery(
        key = Unit,
        flow = { repository.flowOrgs() },
        fetch = { repository.requestOrgs(force = it) },
        next = { repository.requestOrgsNext() },
    )
}
