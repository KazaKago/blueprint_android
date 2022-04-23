package com.kazakago.blueprint.data.repository.flowable

import com.kazakago.blueprint.data.api.hierarchy.GithubApi
import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.blueprint.data.cache.global.CacheHolder
import com.kazakago.blueprint.data.cache.hierarchy.GithubCache
import com.kazakago.blueprint.data.cache.hierarchy.GithubOrgStateManager
import com.kazakago.blueprint.data.mapper.response.github.GithubOrgResponseMapper
import com.kazakago.storeflowable.StoreFlowableFactory
import kotlinx.datetime.Clock
import javax.inject.Inject
import kotlin.time.DurationUnit
import kotlin.time.toDuration

internal class GithubOrgFlowableFactory @Inject constructor(
    private val githubApi: GithubApi,
    private val githubCache: GithubCache,
    private val githubOrgResponseMapper: GithubOrgResponseMapper,
    override val flowableDataStateManager: GithubOrgStateManager,
) : StoreFlowableFactory<String, GithubOrgEntity> {

    companion object {
        private val EXPIRED_DURATION = 30.toDuration(DurationUnit.MINUTES)
    }

    override suspend fun loadDataFromCache(param: String): GithubOrgEntity? {
        return githubCache.orgMapCache[param]?.value
    }

    override suspend fun saveDataToCache(newData: GithubOrgEntity?, param: String) {
        githubCache.orgMapCache[param] = if (newData != null) CacheHolder(newData) else null
    }

    override suspend fun fetchDataFromOrigin(param: String): GithubOrgEntity {
        val response = githubApi.getOrg(param)
        return githubOrgResponseMapper.map(response)
    }

    override suspend fun needRefresh(cachedData: GithubOrgEntity, param: String): Boolean {
        val createdAt = githubCache.orgMapCache[param]?.createdAt
        return if (createdAt != null) {
            val expiredAt = createdAt + EXPIRED_DURATION
            expiredAt < Clock.System.now()
        } else {
            true
        }
    }
}
