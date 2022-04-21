package com.kazakago.blueprint.data.repository.hierarchy

import com.kazakago.blueprint.data.mapper.entity.github.GithubOrgEntityMapper
import com.kazakago.blueprint.data.mapper.entity.github.GithubRepoEntityMapper
import com.kazakago.blueprint.data.repository.flowable.GithubOrgFlowableFactory
import com.kazakago.blueprint.data.repository.flowable.GithubOrgsFlowableFactory
import com.kazakago.blueprint.data.repository.flowable.GithubReposFlowableFactory
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import com.kazakago.storeflowable.core.FlowLoadingState
import com.kazakago.storeflowable.core.mapContent
import com.kazakago.storeflowable.create
import com.kazakago.storeflowable.pagination.oneway.create
import javax.inject.Inject

internal class GithubRepositoryImpl @Inject constructor(
    private val githubOrgEntityMapper: GithubOrgEntityMapper,
    private val githubRepoEntityMapper: GithubRepoEntityMapper,
    private val githubOrgsFlowableFactory: GithubOrgsFlowableFactory,
    private val githubOrgFlowableFactory: GithubOrgFlowableFactory,
    private val githubReposFlowableFactory: GithubReposFlowableFactory,
) : GithubRepository {

    override fun followOrgs(): FlowLoadingState<List<GithubOrg>> {
        val githubOrgsFlowable = githubOrgsFlowableFactory.create(Unit)
        return githubOrgsFlowable.publish().mapContent {
            githubOrgEntityMapper.map(it)
        }
    }

    override suspend fun refreshOrgs() {
        val githubOrgsFlowable = githubOrgsFlowableFactory.create(Unit)
        githubOrgsFlowable.refresh()
    }

    override suspend fun requestAdditionalOrgs(continueWhenError: Boolean) {
        val githubOrgsFlowable = githubOrgsFlowableFactory.create(Unit)
        githubOrgsFlowable.requestNextData(continueWhenError = continueWhenError)
    }

    override fun followOrg(githubOrgName: GithubOrgName): FlowLoadingState<GithubOrg> {
        val githubOrgFlowable = githubOrgFlowableFactory.create(githubOrgName.value)
        return githubOrgFlowable.publish().mapContent {
            githubOrgEntityMapper.map(it)
        }
    }

    override fun followRepos(githubOrgName: GithubOrgName): FlowLoadingState<List<GithubRepo>> {
        val githubReposFlowable = githubReposFlowableFactory.create(githubOrgName.value)
        return githubReposFlowable.publish().mapContent {
            githubRepoEntityMapper.map(it)
        }
    }

    override suspend fun refreshRepos(githubOrgName: GithubOrgName) {
        val githubReposFlowable = githubReposFlowableFactory.create(githubOrgName.value)
        githubReposFlowable.refresh()
    }

    override suspend fun requestAdditionalRepos(githubOrgName: GithubOrgName, continueWhenError: Boolean) {
        val githubReposFlowable = githubReposFlowableFactory.create(githubOrgName.value)
        githubReposFlowable.requestNextData(continueWhenError = continueWhenError)
    }
}
