package com.kazakago.cleanarchitecture.resource.dao.device

import android.content.Context
import androidx.core.content.pm.PackageInfoCompat

object VersionDao {

    fun getVersionName(context: Context): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    }

    fun getVersionCode(context: Context): Long {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return PackageInfoCompat.getLongVersionCode(packageInfo)
    }

}
