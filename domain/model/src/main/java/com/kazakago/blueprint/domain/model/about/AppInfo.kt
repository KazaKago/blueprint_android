package com.kazakago.blueprint.domain.model.about

import java.io.Serializable
import java.net.URI

data class AppInfo(
    val versionName: VersionName,
    val playStoreUri: URI
) : Serializable
