package com.kazakago.cleanarchitecture.web.api

import android.content.Context
import com.kazakago.cleanarchitecture.web.parser.MoshiBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.net.URL

class RetrofitBuilder(context: Context, baseUrl: URL) {

    private val chuckInterceptor = ChuckInterceptor(context)
        .showNotification(false)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(chuckInterceptor)
        .build()

    private val moshiConverter = MoshiConverterFactory.create(MoshiBuilder().build())

    private val builder = Retrofit.Builder()
        .baseUrl(baseUrl.toString())
        .client(okHttpClient)
        .addConverterFactory(moshiConverter)

    fun build(): Retrofit {
        return builder.build()
    }

}