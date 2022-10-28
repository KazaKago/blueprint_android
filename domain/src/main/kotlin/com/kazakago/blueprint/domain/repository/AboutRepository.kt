package com.kazakago.blueprint.domain.repository

import com.kazakago.blueprint.domain.model.about.AppInfo
import com.kazakago.blueprint.domain.model.about.DeveloperInfo

interface AboutRepository {

    suspend fun getAppInfo(): AppInfo

    suspend fun getDeveloperInfo(): DeveloperInfo
}
