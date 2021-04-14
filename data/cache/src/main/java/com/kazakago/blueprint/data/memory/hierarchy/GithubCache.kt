package com.kazakago.blueprint.data.memory.hierarchy

import com.kazakago.blueprint.data.memory.entity.GithubOrgEntity
import com.kazakago.blueprint.data.memory.entity.GithubRepoEntity
import java.time.LocalDateTime

class GithubCache {
    var orgsCache: List<GithubOrgEntity>? = null
    var orgsCacheCreatedAt: LocalDateTime? = null
    val reposCache: MutableMap<String, List<GithubRepoEntity>?> = mutableMapOf()
    val reposCacheCreatedAt: MutableMap<String, LocalDateTime> = mutableMapOf()
}
