package com.kazakago.blueprint.data.cache.hierarchy

import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.blueprint.data.cache.entity.GithubRepoEntity
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubCache @Inject constructor() {
    var orgsCache: List<GithubOrgEntity>? = null
    var orgsCacheCreatedAt: LocalDateTime? = null
    val reposCache: MutableMap<String, List<GithubRepoEntity>?> = mutableMapOf()
    val reposCacheCreatedAt: MutableMap<String, LocalDateTime> = mutableMapOf()
}
