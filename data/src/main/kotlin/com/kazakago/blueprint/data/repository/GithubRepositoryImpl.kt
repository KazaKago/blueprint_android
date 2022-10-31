package com.kazakago.blueprint.data.repository

import com.kazakago.blueprint.data.api.github.GithubApi
import com.kazakago.blueprint.data.cache.GithubCache
import com.kazakago.blueprint.data.cache.PagingCache
import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.domain.model.github.GithubRepo
import com.kazakago.blueprint.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val githubCache: GithubCache,
) : GithubRepository {

    companion object {
        private const val PER_PAGE = 20
    }

    override fun flowOrgs(): Flow<List<GithubOrg>> {
        return githubCache.githubOrgs.asFlow
    }

    override fun flowOrg(githubOrgName: GithubOrgName): Flow<GithubOrg> {
        return githubCache.githubOrgs.asFlow.map { githubOrgs ->
            githubOrgs.firstOrNull { it.name == githubOrgName } ?: run {
                githubApi.getOrg(githubOrgName.value).toModel()
            }
        }
    }

    override suspend fun requestOrgs(force: Boolean) {
        githubCache.githubOrgs.fetch(force) {
            val response = githubApi.getOrgs(null, PER_PAGE)
            val newData = response.map { it.toModel() }
            val newNextPage = response.lastOrNull()?.id
            PagingCache.Result(newData, newNextPage)
        }
    }

    override suspend fun requestOrgsNext() {
        githubCache.githubOrgs.fetchNext { nextPage ->
            val response = githubApi.getOrgs(nextPage, PER_PAGE)
            val newData = response.map { it.toModel() }
            val newNextPage = response.lastOrNull()?.id
            PagingCache.Result(newData, newNextPage)
        }
    }

    override fun flowRepos(githubOrgName: GithubOrgName): Flow<List<GithubRepo>> {
        return githubCache.githubRepos[githubOrgName].asFlow
    }

    override suspend fun requestRepos(force: Boolean, githubOrgName: GithubOrgName) {
        githubCache.githubRepos[githubOrgName].fetch(force) {
            val response = githubApi.getRepos(githubOrgName.value, 1, PER_PAGE)
            val newData = response.map { it.toModel() }
            val newNextPage = 2
            PagingCache.Result(newData, newNextPage)
        }
    }

    override suspend fun requestReposNext(githubOrgName: GithubOrgName) {
        githubCache.githubRepos[githubOrgName].fetchNext { nextPage ->
            val response = githubApi.getRepos(githubOrgName.value, nextPage, PER_PAGE)
            val newData = response.map { it.toModel() }
            val newNextPage = if (response.isNotEmpty()) {
                (((githubCache.githubRepos[githubOrgName].data?.size ?: 0) + response.size) / PER_PAGE) + 1
            } else {
                null
            }
            PagingCache.Result(newData, newNextPage)
        }
    }
}
