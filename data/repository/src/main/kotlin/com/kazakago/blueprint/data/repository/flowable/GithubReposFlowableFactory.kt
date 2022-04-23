package com.kazakago.blueprint.data.repository.flowable

import com.kazakago.blueprint.data.api.hierarchy.GithubApi
import com.kazakago.blueprint.data.cache.entity.GithubRepoEntity
import com.kazakago.blueprint.data.cache.global.CacheHolder
import com.kazakago.blueprint.data.cache.hierarchy.GithubCache
import com.kazakago.blueprint.data.cache.hierarchy.GithubReposStateManager
import com.kazakago.blueprint.data.mapper.response.github.GithubRepoResponseMapper
import com.kazakago.storeflowable.pagination.oneway.Fetched
import com.kazakago.storeflowable.pagination.oneway.PaginationStoreFlowableFactory
import kotlinx.datetime.Clock
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

internal class GithubReposFlowableFactory @Inject constructor(
    private val githubApi: GithubApi,
    private val githubCache: GithubCache,
    private val githubRepoResponseMapper: GithubRepoResponseMapper,
    override val flowableDataStateManager: GithubReposStateManager,
) : PaginationStoreFlowableFactory<String, List<GithubRepoEntity>> {

    companion object {
        private val EXPIRED_DURATION = 30.toDuration(DurationUnit.MINUTES)
        private const val PER_PAGE = 20
    }

    override suspend fun loadDataFromCache(param: String): List<GithubRepoEntity>? {
        return githubCache.reposMapCache[param]?.value
    }

    override suspend fun saveDataToCache(newData: List<GithubRepoEntity>?, param: String) {
        githubCache.reposMapCache[param] = if (newData != null) CacheHolder(newData) else null
    }

    override suspend fun saveNextDataToCache(cachedData: List<GithubRepoEntity>, newData: List<GithubRepoEntity>, param: String) {
        githubCache.reposMapCache[param] = CacheHolder(
            value = cachedData + newData,
            createdAt = githubCache.reposMapCache[param]?.createdAt ?: Clock.System.now(),
        )
    }

    override suspend fun fetchDataFromOrigin(param: String): Fetched<List<GithubRepoEntity>> {
        val response = githubApi.getRepos(param, 1, PER_PAGE)
        val data = response.map { githubRepoResponseMapper.map(it) }
        return Fetched(
            data = data,
            nextKey = if (data.isNotEmpty()) 2.toString() else null,
        )
    }

    override suspend fun fetchNextDataFromOrigin(nextKey: String, param: String): Fetched<List<GithubRepoEntity>> {
        val nextPage = nextKey.toInt()
        val response = githubApi.getRepos(param, nextPage, PER_PAGE)
        val data = response.map { githubRepoResponseMapper.map(it) }
        return Fetched(
            data = data,
            nextKey = if (data.isNotEmpty()) (nextPage + 1).toString() else null,
        )
    }

    override suspend fun needRefresh(cachedData: List<GithubRepoEntity>, param: String): Boolean {
        val createdAt = githubCache.reposMapCache[param]?.createdAt
        return if (createdAt != null) {
            val expiredAt = createdAt + EXPIRED_DURATION
            expiredAt < Clock.System.now()
        } else {
            true
        }
    }
}
