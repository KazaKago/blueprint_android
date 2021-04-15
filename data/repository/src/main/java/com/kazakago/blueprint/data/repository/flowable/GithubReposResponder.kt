package com.kazakago.blueprint.data.repository.flowable

import com.kazakago.blueprint.data.api.hierarchy.GithubService
import com.kazakago.blueprint.data.mapper.response.github.GithubRepoResponseMapper
import com.kazakago.blueprint.data.cache.entity.GithubRepoEntity
import com.kazakago.blueprint.data.cache.hierarchy.GithubCache
import com.kazakago.blueprint.data.cache.hierarchy.GithubReposStateManager
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.storeflowable.FetchingResult
import com.kazakago.storeflowable.pagination.PaginatingStoreFlowableResponder
import java.time.Duration
import java.time.LocalDateTime

internal class GithubReposResponder(
    private val githubService: GithubService,
    private val githubCache: GithubCache,
    private val githubRepoResponseMapper: GithubRepoResponseMapper,
    private val githubOrgName: GithubOrgName,
) : PaginatingStoreFlowableResponder<String, List<GithubRepoEntity>> {

    companion object {
        private val EXPIRED_DURATION = Duration.ofMinutes(30)
        private const val PER_PAGE = 20
    }

    override val flowableDataStateManager = GithubReposStateManager

    override val key = githubOrgName.value

    override suspend fun loadData(): List<GithubRepoEntity>? {
        return githubCache.reposCache[githubOrgName.value]
    }

    override suspend fun saveData(newData: List<GithubRepoEntity>?) {
        githubCache.reposCache[githubOrgName.value] = newData
        githubCache.reposCacheCreatedAt[githubOrgName.value] = LocalDateTime.now()
    }

    override suspend fun saveAdditionalData(cachedData: List<GithubRepoEntity>?, fetchedData: List<GithubRepoEntity>) {
        githubCache.reposCache[key] = (cachedData ?: emptyList()) + fetchedData
    }

    override suspend fun fetchOrigin(): FetchingResult<List<GithubRepoEntity>> {
        val response = githubService.getRepos(githubOrgName.value, 1, PER_PAGE)
        val data = response.map { githubRepoResponseMapper.map(it) }
        return FetchingResult(data = data, noMoreAdditionalData = data.isEmpty())
    }

    override suspend fun fetchAdditionalOrigin(cachedData: List<GithubRepoEntity>?): FetchingResult<List<GithubRepoEntity>> {
        val page = ((cachedData?.size ?: 0) / PER_PAGE + 1)
        val response = githubService.getRepos(githubOrgName.value, page, PER_PAGE)
        val data = response.map { githubRepoResponseMapper.map(it) }
        return FetchingResult(data = data, noMoreAdditionalData = data.isEmpty())
    }

    override suspend fun needRefresh(cachedData: List<GithubRepoEntity>): Boolean {
        val createdAt = githubCache.reposCacheCreatedAt[key]
        return if (createdAt != null) {
            val expiredAt = createdAt + EXPIRED_DURATION
            expiredAt < LocalDateTime.now()
        } else {
            true
        }
    }
}
