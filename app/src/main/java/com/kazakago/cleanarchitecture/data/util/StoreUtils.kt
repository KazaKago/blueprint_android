package com.kazakago.cleanarchitecture.data.util

import android.content.Context

object StoreUtils {

    fun getStoreAppLink(context: Context): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return "market://details?id=" + packageInfo.packageName
    }

    fun getBrowserAppLink(context: Context): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return "http://play.google.com/store/apps/details?id=" + packageInfo.packageName
    }

}
