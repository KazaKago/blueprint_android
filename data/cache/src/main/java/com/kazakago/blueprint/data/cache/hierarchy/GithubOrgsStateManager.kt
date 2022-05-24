package com.kazakago.blueprint.data.cache.hierarchy

import com.kazakago.storeflowable.FlowableDataStateManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubOrgsStateManager @Inject constructor() : FlowableDataStateManager<Unit>()
