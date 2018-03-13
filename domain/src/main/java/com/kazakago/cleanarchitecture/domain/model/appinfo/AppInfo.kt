package com.kazakago.cleanarchitecture.domain.model.appinfo

import java.io.Serializable
import java.net.URI
import java.net.URL

data class AppInfo(
        val developerName: String,
        val versionName: String,
        val versionCode: Int,
        val playStore: URI,
        val mailAddress: String,
        val officialSite: URL
) : Serializable {

    fun getEmailUri(): URI {
        return URI("mailto:$mailAddress")
    }

}