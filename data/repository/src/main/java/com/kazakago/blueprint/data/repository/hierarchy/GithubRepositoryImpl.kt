package com.kazakago.blueprint.data.repository.hierarchy

import com.kazakago.blueprint.data.api.hierarchy.GithubService
import com.kazakago.blueprint.data.cache.hierarchy.GithubCache
import com.kazakago.blueprint.data.mapper.entity.github.GithubOrgEntityMapper
import com.kazakago.blueprint.data.mapper.entity.github.GithubRepoEntityMapper
import com.kazakago.blueprint.data.mapper.response.github.GithubOrgResponseMapper
import com.kazakago.blueprint.data.mapper.response.github.GithubRepoResponseMapper
import com.kazakago.blueprint.data.repository.flowable.GithubOrgsFlowableFactory
import com.kazakago.blueprint.data.repository.flowable.GithubReposFlowableFactory
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import com.kazakago.storeflowable.core.FlowLoadingState
import com.kazakago.storeflowable.core.mapContent
import com.kazakago.storeflowable.pagination.oneway.create
import javax.inject.Inject

internal class GithubRepositoryImpl @Inject constructor(
    private val githubService: GithubService,
    private val githubCache: GithubCache,
    private val githubOrgResponseMapper: GithubOrgResponseMapper,
    private val githubRepoResponseMapper: GithubRepoResponseMapper,
    private val githubOrgEntityMapper: GithubOrgEntityMapper,
    private val githubRepoEntityMapper: GithubRepoEntityMapper,
) : GithubRepository {

    override fun followOrgs(): FlowLoadingState<List<GithubOrg>> {
        val githubOrgsFlowable = GithubOrgsFlowableFactory(githubService, githubCache, githubOrgResponseMapper).create()
        return githubOrgsFlowable.publish().mapContent {
            it.map { githubOrgEntity -> githubOrgEntityMapper.map(githubOrgEntity) }
        }
    }

    override suspend fun refreshOrgs() {
        val githubOrgsFlowable = GithubOrgsFlowableFactory(githubService, githubCache, githubOrgResponseMapper).create()
        githubOrgsFlowable.refresh()
    }

    override suspend fun requestAdditionalOrgs(continueWhenError: Boolean) {
        val githubOrgsFlowable = GithubOrgsFlowableFactory(githubService, githubCache, githubOrgResponseMapper).create()
        githubOrgsFlowable.requestNextData(continueWhenError = continueWhenError)
    }

    override fun followRepos(githubOrgName: GithubOrgName): FlowLoadingState<List<GithubRepo>> {
        val githubReposFlowable = GithubReposFlowableFactory(githubService, githubCache, githubRepoResponseMapper, githubOrgName).create()
        return githubReposFlowable.publish().mapContent {
            it.map { githubRepoEntity -> githubRepoEntityMapper.map(githubRepoEntity) }
        }
    }

    override suspend fun refreshRepos(githubOrgName: GithubOrgName) {
        val githubReposFlowable = GithubReposFlowableFactory(githubService, githubCache, githubRepoResponseMapper, githubOrgName).create()
        githubReposFlowable.refresh()
    }

    override suspend fun requestAdditionalRepos(githubOrgName: GithubOrgName, continueWhenError: Boolean) {
        val githubReposFlowable = GithubReposFlowableFactory(githubService, githubCache, githubRepoResponseMapper, githubOrgName).create()
        githubReposFlowable.requestNextData(continueWhenError = continueWhenError)
    }
}
