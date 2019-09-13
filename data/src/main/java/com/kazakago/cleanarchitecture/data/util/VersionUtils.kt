package com.kazakago.cleanarchitecture.data.util

import android.content.Context
import androidx.core.content.pm.PackageInfoCompat
import com.kazakago.cleanarchitecture.domain.model.about.VersionCode
import com.kazakago.cleanarchitecture.domain.model.about.VersionName

object VersionUtils {

    fun getVersionName(context: Context): VersionName {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return VersionName(packageInfo.versionName)
    }

    fun getVersionCode(context: Context): VersionCode {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return VersionCode(PackageInfoCompat.getLongVersionCode(packageInfo))
    }

}
