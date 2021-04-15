package com.kazakago.blueprint.domain.repository.hierarchy

import com.kazakago.blueprint.domain.model.hierarchy.about.AppInfo
import com.kazakago.blueprint.domain.model.hierarchy.about.DeveloperInfo

interface AboutRepository {

    suspend fun getAppInfo(): AppInfo

    suspend fun getDeveloperInfo(): DeveloperInfo
}
