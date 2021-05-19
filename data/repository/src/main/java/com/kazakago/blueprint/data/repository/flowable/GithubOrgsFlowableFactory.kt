package com.kazakago.blueprint.data.repository.flowable

import com.kazakago.blueprint.data.api.hierarchy.GithubService
import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.blueprint.data.cache.hierarchy.GithubCache
import com.kazakago.blueprint.data.cache.hierarchy.GithubOrgsStateManager
import com.kazakago.blueprint.data.mapper.response.github.GithubOrgResponseMapper
import com.kazakago.storeflowable.FetchingResult
import com.kazakago.storeflowable.pagination.PaginatingStoreFlowableFactory
import java.time.Duration
import java.time.LocalDateTime

internal class GithubOrgsFlowableFactory(
    private val githubService: GithubService,
    private val githubCache: GithubCache,
    private val githubOrgResponseMapper: GithubOrgResponseMapper,
) : PaginatingStoreFlowableFactory<Unit, List<GithubOrgEntity>> {

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

    override suspend fun saveAdditionalDataToCache(cachedData: List<GithubOrgEntity>?, newData: List<GithubOrgEntity>) {
        githubCache.orgsCache = (cachedData ?: emptyList()) + newData
    }

    override suspend fun fetchDataFromOrigin(): FetchingResult<List<GithubOrgEntity>> {
        val response = githubService.getOrgs(null, PER_PAGE)
        val data = response.map { githubOrgResponseMapper.map(it) }
        return FetchingResult(data = data, noMoreAdditionalData = data.isEmpty())
    }

    override suspend fun fetchAdditionalDataFromOrigin(cachedData: List<GithubOrgEntity>?): FetchingResult<List<GithubOrgEntity>> {
        val since = cachedData?.lastOrNull()?.id
        val response = githubService.getOrgs(since, PER_PAGE)
        val data = response.map { githubOrgResponseMapper.map(it) }
        return FetchingResult(data = data, noMoreAdditionalData = data.isEmpty())
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
