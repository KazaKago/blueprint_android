package com.kazakago.blueprint.data.resource.hierarchy.device

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class VersionDao @Inject constructor(@ApplicationContext private val context: Context) {

    fun getVersionName(): String {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageInfo.versionName
    }
}
