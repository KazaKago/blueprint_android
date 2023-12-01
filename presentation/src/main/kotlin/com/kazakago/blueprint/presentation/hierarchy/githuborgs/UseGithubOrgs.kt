package com.kazakago.blueprint.presentation.hierarchy.githuborgs

import androidx.compose.runtime.Composable
import com.kazakago.blueprint.data.api.github.GithubApi
import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.presentation.global.util.LocalGithubApi
import com.kazakago.swr.compose.state.SWRInfiniteState
import com.kazakago.swr.compose.useSWRInfinite
import java.io.Serializable

data class GithubOrgKey(val value: Long) : Serializable

private val getKey: (pageIndex: Int, previousPageData: List<GithubOrg>?) -> GithubOrgKey? = { _, previousPageData ->
    if (previousPageData != null && previousPageData.isEmpty()) null
    else GithubOrgKey(previousPageData?.last()?.id?.value ?: 0)
}

private val fetcher: suspend (key: GithubOrgKey, githubApi: GithubApi) -> List<GithubOrg> = { key, githubApi ->
    val response = githubApi.getOrgs(key.value, 20)
    response.map { it.toModel() }
}

@Composable
fun useGithubOrgs(): SWRInfiniteState<GithubOrgKey, List<GithubOrg>> {
    val githubApi = LocalGithubApi.current
    return useSWRInfinite(
        getKey = getKey,
        fetcher = { fetcher(it, githubApi) },
    )
}
