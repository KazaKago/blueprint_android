package com.kazakago.blueprint.data.resource.hierarchy.device

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.URI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StoreDao @Inject constructor(@ApplicationContext private val context: Context) {

    fun getStoreAppLink(): URI {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return URI("market://details?id=" + packageInfo.packageName)
    }
}
