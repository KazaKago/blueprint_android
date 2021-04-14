package com.kazakago.blueprint.data.repository.flowable

import com.kazakago.blueprint.data.api.hierarchy.GithubService
import com.kazakago.blueprint.data.mapper.hierarchy.response.github.GithubOrgResponseMapper
import com.kazakago.blueprint.data.memory.entity.GithubOrgEntity
import com.kazakago.blueprint.data.memory.hierarchy.GithubCache
import com.kazakago.blueprint.data.memory.hierarchy.GithubOrgsStateManager
import com.kazakago.storeflowable.FetchingResult
import com.kazakago.storeflowable.pagination.PaginatingStoreFlowableResponder
import java.time.Duration
import java.time.LocalDateTime

internal class GithubOrgsResponder(
    private val githubService: GithubService,
    private val githubCache: GithubCache,
    private val githubOrgResponseMapper: GithubOrgResponseMapper,
) : PaginatingStoreFlowableResponder<Unit, List<GithubOrgEntity>> {

    companion object {
        private val EXPIRED_DURATION = Duration.ofMinutes(30)
        private const val PER_PAGE = 20
    }

    override val flowableDataStateManager = GithubOrgsStateManager

    override val key = Unit

    override suspend fun loadData(): List<GithubOrgEntity>? {
        return githubCache.orgsCache
    }

    override suspend fun saveData(newData: List<GithubOrgEntity>?) {
        githubCache.orgsCache = newData
        githubCache.orgsCacheCreatedAt = LocalDateTime.now()
    }

    override suspend fun saveAdditionalData(cachedData: List<GithubOrgEntity>?, fetchedData: List<GithubOrgEntity>) {
        githubCache.orgsCache = (cachedData ?: emptyList()) + fetchedData
    }

    override suspend fun fetchOrigin(): FetchingResult<List<GithubOrgEntity>> {
        val response = githubService.getOrgs(null, PER_PAGE)
        val data = response.map { githubOrgResponseMapper.map(it) }
        return FetchingResult(data = data, noMoreAdditionalData = data.isEmpty())
    }

    override suspend fun fetchAdditionalOrigin(cachedData: List<GithubOrgEntity>?): FetchingResult<List<GithubOrgEntity>> {
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
