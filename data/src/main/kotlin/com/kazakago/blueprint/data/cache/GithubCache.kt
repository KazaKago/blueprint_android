package com.kazakago.blueprint.data.cache

import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.domain.model.github.GithubRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubCache @Inject constructor() {
    val githubOrgs: PagingCache<GithubOrg, Long> = PagingCache()
    val githubRepos: KeyedPagingCache<GithubOrgName, GithubRepo, Int> = KeyedPagingCache()
}
