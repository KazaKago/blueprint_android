package com.kazakago.blueprint.domain.repository.hierarchy

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.storeflowable.core.FlowLoadingState

interface GithubRepository {

    fun getOrgsFlow(): FlowLoadingState<List<GithubOrg>>

    suspend fun refreshOrgs()

    suspend fun requestAdditionalOrgs(continueWhenError: Boolean)

    fun getOrgFlow(githubOrgName: GithubOrgName): FlowLoadingState<GithubOrg>

    suspend fun refreshOrg(githubOrgName: GithubOrgName)

    fun getReposFlow(githubOrgName: GithubOrgName): FlowLoadingState<List<GithubRepo>>

    suspend fun refreshRepos(githubOrgName: GithubOrgName)

    suspend fun requestAdditionalRepos(githubOrgName: GithubOrgName, continueWhenError: Boolean)
}
