package com.kazakago.blueprint.data.repository.flowable

import com.kazakago.blueprint.data.api.hierarchy.GithubService
import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.blueprint.data.cache.hierarchy.GithubCache
import com.kazakago.blueprint.data.cache.hierarchy.GithubOrgsStateManager
import com.kazakago.blueprint.data.mapper.response.github.GithubOrgResponseMapper
import com.kazakago.storeflowable.pagination.oneway.Fetched
import com.kazakago.storeflowable.pagination.oneway.PaginationStoreFlowableFactory
import java.time.Duration
import java.time.LocalDateTime

internal class GithubOrgsFlowableFactory(
    private val githubService: GithubService,
    private val githubCache: GithubCache,
    private val githubOrgResponseMapper: GithubOrgResponseMapper,
) : PaginationStoreFlowableFactory<Unit, List<GithubOrgEntity>> {

    companion object {
        private val EXPIRED_DURATION = Duration.ofMinutes(30)
        private const val PER_PAGE = 20
    }

    override val flowableDataStateManager = GithubOrgsStateManager

    override val key = Unit

    override suspend fun loadDataFromCache(): List<GithubOrgEntity>? {
        return githubCache.orgsCache
    }

    override suspend fun saveDataToCache(newData: List<GithubOrgEntity>?) {
        githubCache.orgsCache = newData
        githubCache.orgsCacheCreatedAt = LocalDateTime.now()
    }

    override suspend fun saveNextDataToCache(cachedData: List<GithubOrgEntity>, newData: List<GithubOrgEntity>) {
        githubCache.orgsCache = cachedData + newData
    }

    override suspend fun fetchDataFromOrigin(): Fetched<List<GithubOrgEntity>> {
        val response = githubService.getOrgs(null, PER_PAGE)
        val data = response.map { githubOrgResponseMapper.map(it) }
        return Fetched(
            data = data,
            nextKey = data.lastOrNull()?.id?.toString(),
        )
    }

    override suspend fun fetchNextDataFromOrigin(nextKey: String): Fetched<List<GithubOrgEntity>> {
        val response = githubService.getOrgs(nextKey.toLong(), PER_PAGE)
        val data = response.map { githubOrgResponseMapper.map(it) }
        return Fetched(
            data = data,
            nextKey = data.lastOrNull()?.id?.toString(),
        )
    }

    override suspend fun needRefresh(cachedData: List<GithubOrgEntity>): Boolean {
        val createdAt = githubCache.orgsCacheCreatedAt
        return if (createdAt != null) {
            val expiredAt = createdAt + EXPIRED_DURATION
            expiredAt < LocalDateTime.now()
        } else {
            true
        }
    }
}
