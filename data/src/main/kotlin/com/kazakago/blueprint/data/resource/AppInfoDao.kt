package com.kazakago.blueprint.data.resource

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppInfoDao @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    fun getVersionName(): String {
        return getPackageInfo().versionName
    }

    fun getVersionCode(): Long {
        return getPackageInfo().longVersionCode
    }

    private fun getPackageInfo(): PackageInfo {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.packageManager.getPackageInfo(context.packageName, PackageManager.PackageInfoFlags.of(0))
        } else {
            context.packageManager.getPackageInfo(context.packageName, 0)
        }
    }
}
