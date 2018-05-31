package com.kazakago.cleanarchitecture.data.util

import android.content.Context

object VersionUtils {

    fun getVersionName(context: Context): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    }

    fun getVersionCode(context: Context): Long {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.longVersionCode
    }

}
