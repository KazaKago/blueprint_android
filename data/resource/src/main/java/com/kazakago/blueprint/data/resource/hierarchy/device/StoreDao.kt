package com.kazakago.blueprint.data.resource.hierarchy.device

import android.content.Context
import java.net.URI

class StoreDao(private val context: Context) {

    fun getStoreAppLink(): URI {
        val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
        return URI("market://details?id=" + packageInfo.packageName)
    }
}
