package com.kazakago.blueprint.data.repository.fetcher

import com.kazakago.blueprint.data.api.hierarchy.GithubApi
import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.blueprint.data.mapper.response.github.GithubOrgResponseMapper
import com.kazakago.storeflowable.fetcher.Fetcher
import javax.inject.Inject

internal class GithubOrgFetcher @Inject constructor(
    private val githubApi: GithubApi,
    private val githubOrgResponseMapper: GithubOrgResponseMapper,
) : Fetcher<String, GithubOrgEntity> {

    override suspend fun fetch(param: String): GithubOrgEntity {
        val response = githubApi.getOrg(param)
        return githubOrgResponseMapper.map(response)
    }
}
