package com.kazakago.blueprint.data.cache

import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.domain.model.github.GithubRepo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubCache @Inject constructor() {
    val githubOrgs: Cache<List<GithubOrg>> = Cache()
    val githubOrgsNextKey: Cache<Long> = Cache()
    val githubRepos: KeyedCache<GithubOrgName, List<GithubRepo>> = KeyedCache()
    val githubReposNextPages: KeyedCache<GithubOrgName, Int?> = KeyedCache()
}
