package com.kazakago.blueprint.data.repository.fetcher

import com.kazakago.blueprint.data.api.hierarchy.GithubApi
import com.kazakago.blueprint.data.cache.entity.GithubRepoEntity
import com.kazakago.blueprint.data.mapper.response.github.GithubRepoResponseMapper
import com.kazakago.storeflowable.fetcher.PaginationFetcher
import javax.inject.Inject

internal class GithubReposFetcher @Inject constructor(
    private val githubApi: GithubApi,
    private val githubRepoResponseMapper: GithubRepoResponseMapper,
) : PaginationFetcher<String, GithubRepoEntity> {

    companion object {
        private const val PER_PAGE = 20
    }

    override suspend fun fetch(param: String): PaginationFetcher.Result<GithubRepoEntity> {
        val response = githubApi.getRepos(param, 1, PER_PAGE)
        val data = response.map { githubRepoResponseMapper.map(it) }
        return PaginationFetcher.Result(
            data = data,
            nextRequestKey = if (data.isNotEmpty()) 2.toString() else null,
        )
    }

    override suspend fun fetchNext(nextKey: String, param: String): PaginationFetcher.Result<GithubRepoEntity> {
        val nextPage = nextKey.toInt()
        val response = githubApi.getRepos(param, nextPage, PER_PAGE)
        val data = response.map { githubRepoResponseMapper.map(it) }
        return PaginationFetcher.Result(
            data = data,
            nextRequestKey = if (data.isNotEmpty()) (nextPage + 1).toString() else null,
        )
    }
}
