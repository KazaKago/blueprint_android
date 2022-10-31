package com.kazakago.blueprint.domain.repository

import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.domain.model.github.GithubRepo
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    fun flowOrgs(): Flow<List<GithubOrg>>

    fun flowOrg(githubOrgName: GithubOrgName): Flow<GithubOrg>

    suspend fun requestOrgs(force: Boolean)

    suspend fun requestOrgsNext()

    fun flowRepos(githubOrgName: GithubOrgName): Flow<List<GithubRepo>>

    suspend fun requestRepos(force: Boolean, githubOrgName: GithubOrgName)

    suspend fun requestReposNext(githubOrgName: GithubOrgName)
}
