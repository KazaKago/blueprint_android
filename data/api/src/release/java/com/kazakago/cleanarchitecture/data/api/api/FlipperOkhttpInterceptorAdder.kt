package com.kazakago.cleanarchitecture.data.api.api

import android.content.Context
import okhttp3.OkHttpClient

@Suppress("UNUSED_PARAMETER")
fun OkHttpClient.Builder.addFlipperOkhttpInterceptor(context: Context): OkHttpClient.Builder {
    //do nothing.
    return this
}
