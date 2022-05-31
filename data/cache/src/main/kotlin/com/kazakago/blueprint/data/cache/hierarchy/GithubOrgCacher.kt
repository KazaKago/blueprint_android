package com.kazakago.blueprint.data.cache.hierarchy

import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.storeflowable.cacher.Cacher
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubOrgCacher @Inject constructor() : Cacher<String, GithubOrgEntity>()
