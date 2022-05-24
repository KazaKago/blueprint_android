package com.kazakago.blueprint.domain.model.hierarchy.about

import java.io.Serializable

data class AboutInfo(
    val appInfo: AppInfo,
    val developerInfo: DeveloperInfo
) : Serializable
