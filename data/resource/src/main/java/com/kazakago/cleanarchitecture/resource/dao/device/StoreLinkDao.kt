package com.kazakago.cleanarchitecture.resource.dao.device

import android.content.Context
import java.net.URI
import java.net.URL

object StoreLinkDao {

    fun getStoreAppLink(context: Context): URI {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return URI("market://details?id=" + packageInfo.packageName)
    }

    fun getBrowserAppLink(context: Context): URL {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return URL("http://play.google.com/store/apps/details?id=" + packageInfo.packageName)
    }

}
