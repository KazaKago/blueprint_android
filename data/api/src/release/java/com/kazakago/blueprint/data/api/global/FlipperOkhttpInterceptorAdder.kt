package com.kazakago.blueprint.data.api.global

import android.content.Context
import okhttp3.OkHttpClient

@Suppress("UNUSED_PARAMETER")
fun OkHttpClient.Builder.addFlipperOkhttpInterceptor(context: Context): OkHttpClient.Builder {
    //do nothing.
    return this
}
