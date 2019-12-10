package com.kazakago.cleanarchitecture.repository.about

import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.about.DeveloperInfo

interface AboutRepository {

    fun getAppInfo(): AppInfo

    fun getDeveloperInfo(): DeveloperInfo

}
