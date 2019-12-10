package com.kazakago.cleanarchitecture.model.about

import java.io.Serializable
import java.net.URI

data class AppInfo(
    val versionName: VersionName,
    val versionCode: VersionCode,
    val playStoreUri: URI
) : Serializable