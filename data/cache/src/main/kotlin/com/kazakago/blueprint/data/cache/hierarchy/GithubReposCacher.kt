package com.kazakago.blueprint.data.cache.hierarchy

import com.kazakago.blueprint.data.cache.entity.GithubRepoEntity
import com.kazakago.storeflowable.cacher.PaginationCacher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubReposCacher @Inject constructor() : PaginationCacher<String, GithubRepoEntity>()
