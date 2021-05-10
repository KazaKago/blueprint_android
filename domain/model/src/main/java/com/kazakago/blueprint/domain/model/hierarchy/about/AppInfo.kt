package com.kazakago.blueprint.domain.model.hierarchy.about

import java.io.Serializable

data class AppInfo(
    val versionName: VersionName,
    val versionCode: VersionCode,
) : Serializable
