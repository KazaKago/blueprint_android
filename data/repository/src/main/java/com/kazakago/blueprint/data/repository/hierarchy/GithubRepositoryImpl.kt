package com.kazakago.blueprint.data.repository.hierarchy

import com.kazakago.blueprint.data.api.hierarchy.GithubService
import com.kazakago.blueprint.data.cache.hierarchy.GithubCache
import com.kazakago.blueprint.data.mapper.entity.github.GithubOrgEntityMapper
import com.kazakago.blueprint.data.mapper.entity.github.GithubRepoEntityMapper
import com.kazakago.blueprint.data.mapper.response.github.GithubOrgResponseMapper
import com.kazakago.blueprint.data.mapper.response.github.GithubRepoResponseMapper
import com.kazakago.blueprint.data.repository.flowable.GithubOrgsFlowableCallback
import com.kazakago.blueprint.data.repository.flowable.GithubReposFlowableCallback
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import com.kazakago.storeflowable.core.FlowableState
import com.kazakago.storeflowable.core.mapContent
import com.kazakago.storeflowable.pagination.create
import javax.inject.Inject

internal class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService,
    private val githubCache: GithubCache,
    private val githubOrgResponseMapper: GithubOrgResponseMapper,
    private val githubRepoResponseMapper: GithubRepoResponseMapper,
    private val githubOrgEntityMapper: GithubOrgEntityMapper,
    private val githubRepoEntityMapper: GithubRepoEntityMapper,
) : GithubRepository {

    override fun followOrgs(): FlowableState<List<GithubOrg>> {
        val githubOrgsFlowable = GithubOrgsFlowableCallback(githubService, githubCache, githubOrgResponseMapper).create()
        return githubOrgsFlowable.publish().mapContent {
            it.map { githubOrgEntity -> githubOrgEntityMapper.map(githubOrgEntity) }
        }
    }

    override suspend fun refreshOrgs() {
        val githubOrgsFlowable = GithubOrgsFlowableCallback(githubService, githubCache, githubOrgResponseMapper).create()
        githubOrgsFlowable.refresh()
    }

    override suspend fun requestAdditionalOrgs(continueWhenError: Boolean) {
        val githubOrgsFlowable = GithubOrgsFlowableCallback(githubService, githubCache, githubOrgResponseMapper).create()
        githubOrgsFlowable.requestAdditionalData(continueWhenError = continueWhenError)
    }

    override fun followRepos(githubOrgName: GithubOrgName): FlowableState<List<GithubRepo>> {
        val githubReposFlowable = GithubReposFlowableCallback(githubService, githubCache, githubRepoResponseMapper, githubOrgName).create()
        return githubReposFlowable.publish().mapContent {
            it.map { githubRepoEntity -> githubRepoEntityMapper.map(githubRepoEntity) }
        }
    }

    override suspend fun refreshRepos(githubOrgName: GithubOrgName) {
        val githubReposFlowable = GithubReposFlowableCallback(githubService, githubCache, githubRepoResponseMapper, githubOrgName).create()
        githubReposFlowable.refresh()
    }

    override suspend fun requestAdditionalRepos(githubOrgName: GithubOrgName, continueWhenError: Boolean) {
        val githubReposFlowable = GithubReposFlowableCallback(githubService, githubCache, githubRepoResponseMapper, githubOrgName).create()
        githubReposFlowable.requestAdditionalData(continueWhenError = continueWhenError)
    }
}
