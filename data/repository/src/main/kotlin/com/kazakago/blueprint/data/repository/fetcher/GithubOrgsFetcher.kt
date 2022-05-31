package com.kazakago.blueprint.data.repository.fetcher

import com.kazakago.blueprint.data.api.hierarchy.GithubApi
import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.blueprint.data.mapper.response.github.GithubOrgResponseMapper
import com.kazakago.storeflowable.fetcher.PaginationFetcher
import javax.inject.Inject

internal class GithubOrgsFetcher @Inject constructor(
    private val githubApi: GithubApi,
    private val githubOrgResponseMapper: GithubOrgResponseMapper,
) : PaginationFetcher<Unit, GithubOrgEntity> {

    companion object {
        private const val PER_PAGE = 20
    }

    override suspend fun fetch(param: Unit): PaginationFetcher.Result<GithubOrgEntity> {
        val response = githubApi.getOrgs(null, PER_PAGE)
        val data = response.map { githubOrgResponseMapper.map(it) }
        return PaginationFetcher.Result(
            data = data,
            nextRequestKey = data.lastOrNull()?.id?.toString(),
        )
    }

    override suspend fun fetchNext(nextKey: String, param: Unit): PaginationFetcher.Result<GithubOrgEntity> {
        val response = githubApi.getOrgs(nextKey.toLong(), PER_PAGE)
        val data = response.map { githubOrgResponseMapper.map(it) }
        return PaginationFetcher.Result(
            data = data,
            nextRequestKey = data.lastOrNull()?.id?.toString(),
        )
    }
}
