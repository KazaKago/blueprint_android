package com.kazakago.cleanarchitecture.data.util

import android.content.Context
import android.content.pm.PackageManager

/**
 * PlayStore Related Utilities
 *
 * @author PTAMURA
 */
object StoreUtils {

    fun getStoreAppLink(context: Context): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return "market://details?id=" + packageInfo.packageName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return ""
        }
    }

    fun getBrowserAppLink(context: Context): String {
        try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return "http://play.google.com/store/apps/details?id=" + packageInfo.packageName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            return ""
        }
    }

}
