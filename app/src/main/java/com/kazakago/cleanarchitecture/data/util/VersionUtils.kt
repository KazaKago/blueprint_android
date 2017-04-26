package com.kazakago.cleanarchitecture.data.util

import android.content.Context
import android.content.pm.PackageManager

/**
 * Version Related Utilities.
 *
 *
 * Created by tamura_k on 2016/03/18.
 */
object VersionUtils {

    fun getVersionName(context: Context): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return ""
        }
    }

    fun getVersionCode(context: Context): Int {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return 0
        }
    }

}
