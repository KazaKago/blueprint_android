package com.kazakago.cleanarchitecture.domain.usecase.output.about

import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo

data class AboutOutput(
    val appInfo: AppInfo,
    val developerInfo: DeveloperInfo
)