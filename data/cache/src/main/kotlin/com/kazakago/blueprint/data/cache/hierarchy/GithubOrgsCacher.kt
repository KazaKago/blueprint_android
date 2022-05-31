package com.kazakago.blueprint.data.cache.hierarchy

import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.storeflowable.cacher.PaginationCacher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubOrgsCacher @Inject constructor() : PaginationCacher<Unit, GithubOrgEntity>()
