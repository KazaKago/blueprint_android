package com.kazakago.blueprint.data.repository.flowable

import com.kazakago.blueprint.data.api.hierarchy.GithubService
import com.kazakago.blueprint.data.cache.entity.GithubRepoEntity
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
    private val githubService: GithubService,
    private val githubCache: GithubCache,
    private val githubRepoResponseMapper: GithubRepoResponseMapper,
    githubReposStateManager: GithubReposStateManager,
) : PaginationStoreFlowableFactory<String, List<GithubRepoEntity>> {

    companion object {
        private val EXPIRED_DURATION = 30.toDuration(DurationUnit.MINUTES)
        private const val PER_PAGE = 20
    }

    override val flowableDataStateManager = githubReposStateManager

    override suspend fun loadDataFromCache(param: String): List<GithubRepoEntity>? {
        return githubCache.reposCache[param]
    }

    override suspend fun saveDataToCache(newData: List<GithubRepoEntity>?, param: String) {
        githubCache.reposCache[param] = newData
        githubCache.reposCacheCreatedAt[param] = Clock.System.now()
    }

    override suspend fun saveNextDataToCache(cachedData: List<GithubRepoEntity>, newData: List<GithubRepoEntity>, param: String) {
        githubCache.reposCache[param] = cachedData + newData
    }

    override suspend fun fetchDataFromOrigin(param: String): Fetched<List<GithubRepoEntity>> {
        val response = githubService.getRepos(param, 1, PER_PAGE)
        val data = response.map { githubRepoResponseMapper.map(it) }
        return Fetched(
            data = data,
            nextKey = if (data.isNotEmpty()) 2.toString() else null,
        )
    }

    override suspend fun fetchNextDataFromOrigin(nextKey: String, param: String): Fetched<List<GithubRepoEntity>> {
        val nextPage = nextKey.toInt()
        val response = githubService.getRepos(param, nextPage, PER_PAGE)
        val data = response.map { githubRepoResponseMapper.map(it) }
        return Fetched(
            data = data,
            nextKey = if (data.isNotEmpty()) (nextPage + 1).toString() else null,
        )
    }

    override suspend fun needRefresh(cachedData: List<GithubRepoEntity>, param: String): Boolean {
        val createdAt = githubCache.reposCacheCreatedAt[param]
        return if (createdAt != null) {
            val expiredAt = createdAt + EXPIRED_DURATION
            expiredAt < Clock.System.now()
        } else {
            true
        }
    }
}
