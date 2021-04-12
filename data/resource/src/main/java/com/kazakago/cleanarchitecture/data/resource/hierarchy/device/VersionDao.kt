package com.kazakago.cleanarchitecture.data.resource.hierarchy.device

import android.content.Context
import androidx.core.content.pm.PackageInfoCompat

class VersionDao(private val context: Context) {

    fun getVersionName(): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    }

    fun getVersionCode(): Long {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return PackageInfoCompat.getLongVersionCode(packageInfo)
    }
}
