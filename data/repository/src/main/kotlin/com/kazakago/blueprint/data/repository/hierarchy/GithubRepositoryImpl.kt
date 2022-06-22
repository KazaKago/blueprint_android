package com.kazakago.blueprint.data.repository.hierarchy

import com.kazakago.blueprint.data.cache.hierarchy.GithubOrgCacher
import com.kazakago.blueprint.data.cache.hierarchy.GithubOrgsCacher
import com.kazakago.blueprint.data.cache.hierarchy.GithubReposCacher
import com.kazakago.blueprint.data.mapper.entity.github.GithubOrgEntityMapper
import com.kazakago.blueprint.data.mapper.entity.github.GithubRepoEntityMapper
import com.kazakago.blueprint.data.repository.fetcher.GithubOrgFetcher
import com.kazakago.blueprint.data.repository.fetcher.GithubOrgsFetcher
import com.kazakago.blueprint.data.repository.fetcher.GithubReposFetcher
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import com.kazakago.storeflowable.StoreFlowable
import com.kazakago.storeflowable.core.FlowLoadingState
import com.kazakago.storeflowable.core.mapContent
import com.kazakago.storeflowable.from
import javax.inject.Inject

internal class GithubRepositoryImpl @Inject constructor(
    private val githubOrgEntityMapper: GithubOrgEntityMapper,
    private val githubRepoEntityMapper: GithubRepoEntityMapper,
    private val githubOrgsCacher: GithubOrgsCacher,
    private val githubOrgsFetcher: GithubOrgsFetcher,
    private val githubOrgCacher: GithubOrgCacher,
    private val githubOrgFetcher: GithubOrgFetcher,
    private val githubReposCacher: GithubReposCacher,
    private val githubReposFetcher: GithubReposFetcher,
) : GithubRepository {

    override fun getOrgsFlow(): FlowLoadingState<List<GithubOrg>> {
        val githubOrgsFlowable = StoreFlowable.from(githubOrgsCacher, githubOrgsFetcher)
        return githubOrgsFlowable.publish().mapContent {
            githubOrgEntityMapper.map(it)
        }
    }

    override suspend fun refreshOrgs() {
        val githubOrgsFlowable = StoreFlowable.from(githubOrgsCacher, githubOrgsFetcher)
        githubOrgsFlowable.refresh()
    }

    override suspend fun requestAdditionalOrgs(continueWhenError: Boolean) {
        val githubOrgsFlowable = StoreFlowable.from(githubOrgsCacher, githubOrgsFetcher)
        githubOrgsFlowable.requestNextData(continueWhenError = continueWhenError)
    }

    override fun getOrgFlow(githubOrgName: GithubOrgName): FlowLoadingState<GithubOrg> {
        val githubOrgFlowable = StoreFlowable.from(githubOrgCacher, githubOrgFetcher, githubOrgName.value)
        return githubOrgFlowable.publish().mapContent {
            githubOrgEntityMapper.map(it)
        }
    }

    override suspend fun refreshOrg(githubOrgName: GithubOrgName) {
        val githubOrgFlowable = StoreFlowable.from(githubOrgCacher, githubOrgFetcher, githubOrgName.value)
        githubOrgFlowable.refresh()
    }

    override fun getReposFlow(githubOrgName: GithubOrgName): FlowLoadingState<List<GithubRepo>> {
        val githubReposFlowable = StoreFlowable.from(githubReposCacher, githubReposFetcher, githubOrgName.value)
        return githubReposFlowable.publish().mapContent {
            githubRepoEntityMapper.map(it)
        }
    }

    override suspend fun refreshRepos(githubOrgName: GithubOrgName) {
        val githubReposFlowable = StoreFlowable.from(githubReposCacher, githubReposFetcher, githubOrgName.value)
        githubReposFlowable.refresh()
    }

    override suspend fun requestAdditionalRepos(githubOrgName: GithubOrgName, continueWhenError: Boolean) {
        val githubReposFlowable = StoreFlowable.from(githubReposCacher, githubReposFetcher, githubOrgName.value)
        githubReposFlowable.requestNextData(continueWhenError = continueWhenError)
    }
}
