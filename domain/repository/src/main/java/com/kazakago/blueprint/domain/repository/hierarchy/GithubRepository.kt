package com.kazakago.blueprint.domain.repository.hierarchy

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.storeflowable.core.FlowableState

interface GithubRepository {

    fun followOrgs(): FlowableState<List<GithubOrg>>

    suspend fun refreshOrgs()

    suspend fun requestAdditionalOrgs(continueWhenError: Boolean)

    fun followRepos(githubOrgName: GithubOrgName): FlowableState<List<GithubRepo>>

    suspend fun refreshRepos(githubOrgName: GithubOrgName)

    suspend fun requestAdditionalRepos(githubOrgName: GithubOrgName, continueWhenError: Boolean)
}
