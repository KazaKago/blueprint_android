package com.kazakago.cleanarchitecture.domain.repository.about

import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo

interface AboutRepository {

    fun getAppInfo(): AppInfo

    fun getDeveloperInfo(): DeveloperInfo

}
