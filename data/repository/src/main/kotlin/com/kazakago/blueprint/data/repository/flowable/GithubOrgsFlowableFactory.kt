package com.kazakago.blueprint.data.repository.flowable

import com.kazakago.blueprint.data.api.hierarchy.GithubApi
import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.blueprint.data.cache.hierarchy.GithubCache
import com.kazakago.blueprint.data.cache.hierarchy.GithubOrgsStateManager
import com.kazakago.blueprint.data.mapper.response.github.GithubOrgResponseMapper
import com.kazakago.storeflowable.pagination.oneway.Fetched
import com.kazakago.storeflowable.pagination.oneway.PaginationStoreFlowableFactory
import kotlinx.datetime.Clock
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

internal class GithubOrgsFlowableFactory @Inject constructor(
    private val githubApi: GithubApi,
    private val githubCache: GithubCache,
    private val githubOrgResponseMapper: GithubOrgResponseMapper,
    githubOrgsStateManager: GithubOrgsStateManager,
) : PaginationStoreFlowableFactory<Unit, List<GithubOrgEntity>> {

    companion object {
        private val EXPIRED_DURATION = 30.toDuration(DurationUnit.MINUTES)
        private const val PER_PAGE = 20
    }

    override val flowableDataStateManager = githubOrgsStateManager

    override suspend fun loadDataFromCache(param: Unit): List<GithubOrgEntity>? {
        return githubCache.orgsCache
    }

    override suspend fun saveDataToCache(newData: List<GithubOrgEntity>?, param: Unit) {
        githubCache.orgsCache = newData
        githubCache.orgsCacheCreatedAt = Clock.System.now()
    }

    override suspend fun saveNextDataToCache(cachedData: List<GithubOrgEntity>, newData: List<GithubOrgEntity>, param: Unit) {
        githubCache.orgsCache = cachedData + newData
    }

    override suspend fun fetchDataFromOrigin(param: Unit): Fetched<List<GithubOrgEntity>> {
        val response = githubApi.getOrgs(null, PER_PAGE)
        val data = response.map { githubOrgResponseMapper.map(it) }
        return Fetched(
            data = data,
            nextKey = data.lastOrNull()?.id?.toString(),
        )
    }

    override suspend fun fetchNextDataFromOrigin(nextKey: String, param: Unit): Fetched<List<GithubOrgEntity>> {
        val response = githubApi.getOrgs(nextKey.toLong(), PER_PAGE)
        val data = response.map { githubOrgResponseMapper.map(it) }
        return Fetched(
            data = data,
            nextKey = data.lastOrNull()?.id?.toString(),
        )
    }

    override suspend fun needRefresh(cachedData: List<GithubOrgEntity>, param: Unit): Boolean {
        val createdAt = githubCache.orgsCacheCreatedAt
        return if (createdAt != null) {
            val expiredAt = createdAt + EXPIRED_DURATION
            expiredAt < Clock.System.now()
        } else {
            true
        }
    }
}
