package com.kazakago.blueprint.data.repository

import com.kazakago.blueprint.data.api.github.GithubApi
import com.kazakago.blueprint.data.cache.GithubCache
import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.domain.model.github.GithubRepo
import com.kazakago.blueprint.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class GithubRepositoryImpl @Inject constructor(
    private val githubApi: GithubApi,
    private val githubCache: GithubCache,
) : GithubRepository {

    companion object {
        private const val PER_PAGE = 20
    }

    override fun flowOrgs(): Flow<List<GithubOrg>> {
        return githubCache.githubOrgs.asFlow
    }

    override fun flowOrgs(githubOrgName: GithubOrgName): Flow<GithubOrg> {
        return githubCache.githubOrgs.asFlow.map { githubOrgs ->
            githubOrgs.firstOrNull { it.name == githubOrgName } ?: run {
                githubApi.getOrg(githubOrgName.value).toModel()
            }
        }
    }

    override suspend fun requestOrgs(force: Boolean) {
        if (githubCache.githubOrgs.data == null || force) {
            val response = githubApi.getOrgs(null, PER_PAGE)
            githubCache.githubOrgs.data = response.map { it.toModel() }
            githubCache.githubOrgsNextKey.data = response.lastOrNull()?.id
        }
    }

    override suspend fun requestOrgsNext() {
        if (githubCache.githubOrgsNextKey.data != null) {
            val response = githubApi.getOrgs(githubCache.githubOrgsNextKey.data, PER_PAGE)
            githubCache.githubOrgs.data = githubCache.githubOrgs.data plus response.map { it.toModel() }
            githubCache.githubOrgsNextKey.data = response.lastOrNull()?.id
        }
    }

    override fun flowRepos(githubOrgName: GithubOrgName): Flow<List<GithubRepo>> {
        return githubCache.githubRepos[githubOrgName].asFlow
    }

    override suspend fun requestRepos(force: Boolean, githubOrgName: GithubOrgName) {
        if (githubCache.githubRepos[githubOrgName].data == null || force) {
            val response = githubApi.getRepos(githubOrgName.value, 1, PER_PAGE)
            githubCache.githubRepos[githubOrgName].data = response.map { it.toModel() }
            githubCache.githubReposNextPages[githubOrgName].data = 2
        }
    }

    override suspend fun requestReposNext(githubOrgName: GithubOrgName) {
        if (githubCache.githubReposNextPages[githubOrgName].data != null) {
            val response = githubApi.getRepos(githubOrgName.value, githubCache.githubReposNextPages[githubOrgName].data, PER_PAGE)
            githubCache.githubRepos[githubOrgName].data = githubCache.githubRepos[githubOrgName].data plus response.map { it.toModel() }
            githubCache.githubReposNextPages[githubOrgName].data = if (response.isNotEmpty()) {
                githubCache.githubRepos[githubOrgName].data?.size divisionPlus1 PER_PAGE
            } else null
        }
    }

    private infix fun <T> List<T>?.plus(list: List<T>): List<T> {
        return (this ?: emptyList()) + list
    }

    private infix fun Int?.divisionPlus1(other: Int): Int {
        return ((this ?: 0) / other) + 1
    }
}
