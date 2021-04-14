package com.kazakago.blueprint.data.resource.hierarchy.device

import android.content.Context

class VersionDao(private val context: Context) {

    fun getVersionName(): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    }
}
