package com.kazakago.cleanarchitecture.data.resource.dao.device

import android.content.Context
import java.net.URI
import java.net.URL

class StoreLinkDao(private val context: Context) {

    fun getStoreAppLink(): URI {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return URI("market://details?id=" + packageInfo.packageName)
    }

    fun getBrowserAppLink(): URL {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return URL("http://play.google.com/store/apps/details?id=" + packageInfo.packageName)
    }

}
