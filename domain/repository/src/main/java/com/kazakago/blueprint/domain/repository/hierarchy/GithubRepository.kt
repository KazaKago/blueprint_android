package com.kazakago.blueprint.domain.repository.hierarchy

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.storeflowable.core.FlowLoadingState

interface GithubRepository {

    fun followOrgs(): FlowLoadingState<List<GithubOrg>>

    suspend fun refreshOrgs()

    suspend fun requestAdditionalOrgs(continueWhenError: Boolean)

    fun followOrg(githubOrgName: GithubOrgName): FlowLoadingState<GithubOrg>

    fun followRepos(githubOrgName: GithubOrgName): FlowLoadingState<List<GithubRepo>>

    suspend fun refreshRepos(githubOrgName: GithubOrgName)

    suspend fun requestAdditionalRepos(githubOrgName: GithubOrgName, continueWhenError: Boolean)
}
