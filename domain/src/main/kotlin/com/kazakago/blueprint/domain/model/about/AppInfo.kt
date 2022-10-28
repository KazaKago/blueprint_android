package com.kazakago.blueprint.domain.model.about

import java.io.Serializable

data class AppInfo(
    val versionName: VersionName,
    val versionCode: VersionCode,
) : Serializable
