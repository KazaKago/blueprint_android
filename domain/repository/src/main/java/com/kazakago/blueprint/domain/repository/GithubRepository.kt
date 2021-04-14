package com.kazakago.blueprint.domain.repository

import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.domain.model.github.GithubRepo
import com.kazakago.storeflowable.core.State
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    fun followOrgs(): Flow<State<List<GithubOrg>>>

    suspend fun refreshOrgs()

    suspend fun requestAdditionalOrgs(continueWhenError: Boolean)

    fun followRepos(githubOrgName: GithubOrgName): Flow<State<List<GithubRepo>>>

    suspend fun refreshRepos(githubOrgName: GithubOrgName)

    suspend fun requestAdditionalRepos(githubOrgName: GithubOrgName, continueWhenError: Boolean)
}
