package com.kazakago.blueprint.data.cache.hierarchy

import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.blueprint.data.cache.entity.GithubRepoEntity
import com.kazakago.blueprint.data.cache.global.CacheHolder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubCache @Inject constructor() {
    var orgNameListCache: CacheHolder<List<String>>? = null
    val orgMapCache: MutableMap<String, CacheHolder<GithubOrgEntity>?> = mutableMapOf()
    val reposMapCache: MutableMap<String, CacheHolder<List<GithubRepoEntity>>?> = mutableMapOf()
}
