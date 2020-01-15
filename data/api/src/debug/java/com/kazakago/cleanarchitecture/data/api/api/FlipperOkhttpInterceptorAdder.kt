package com.kazakago.cleanarchitecture.data.api.api

import android.content.Context
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import okhttp3.OkHttpClient

fun OkHttpClient.Builder.addFlipperOkhttpInterceptor(context: Context): OkHttpClient.Builder {
    val flipperOkhttpInterceptor = FlipperOkhttpInterceptor(AndroidFlipperClient.getInstance(context).getPlugin(NetworkFlipperPlugin.ID))
    addNetworkInterceptor(flipperOkhttpInterceptor)
    return this
}