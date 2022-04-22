package com.kazakago.blueprint.data.repository.flowable

import com.kazakago.blueprint.data.api.hierarchy.GithubApi
import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.blueprint.data.cache.hierarchy.GithubCache
import com.kazakago.blueprint.data.cache.hierarchy.GithubOrgStateManager
import com.kazakago.blueprint.data.mapper.response.github.GithubOrgResponseMapper
import com.kazakago.storeflowable.StoreFlowableFactory
import javax.inject.Inject

internal class GithubOrgFlowableFactory @Inject constructor(
    private val githubApi: GithubApi,
    private val githubCache: GithubCache,
    private val githubOrgResponseMapper: GithubOrgResponseMapper,
    githubOrgStateManager: GithubOrgStateManager,
) : StoreFlowableFactory<String, GithubOrgEntity> {

    override val flowableDataStateManager = githubOrgStateManager

    override suspend fun loadDataFromCache(param: String): GithubOrgEntity? {
        return githubCache.orgsCache?.firstOrNull { it.name == param }
    }

    override suspend fun saveDataToCache(newData: GithubOrgEntity?, param: String) {
        githubCache.orgsCache = githubCache.orgsCache?.map {
            if (it.name == newData?.name) newData else it
        }
    }

    override suspend fun fetchDataFromOrigin(param: String): GithubOrgEntity {
        val response = githubApi.getOrg(param)
        return githubOrgResponseMapper.map(response)
    }

    override suspend fun needRefresh(cachedData: GithubOrgEntity, param: String): Boolean {
        return false
    }
}
