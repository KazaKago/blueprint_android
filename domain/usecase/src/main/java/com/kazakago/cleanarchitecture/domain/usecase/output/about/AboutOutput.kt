package com.kazakago.cleanarchitecture.domain.usecase.output.about

import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.DeveloperInfo
import java.io.Serializable

data class AboutOutput(
    val appInfo: AppInfo,
    val developerInfo: DeveloperInfo
) : Serializable
