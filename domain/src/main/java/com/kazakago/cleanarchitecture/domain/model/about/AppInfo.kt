package com.kazakago.cleanarchitecture.domain.model.about

import java.io.Serializable
import java.net.URI

data class AppInfo(
        val versionName: String,
        val versionCode: Long,
        val playStoreUri: URI
) : Serializable