package com.kazakago.cleanarchitecture.usecase.output.about

import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.about.DeveloperInfo

data class AboutOutput(
    val appInfo: AppInfo,
    val developerInfo: DeveloperInfo
)