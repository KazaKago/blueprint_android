package com.kazakago.cleanarchitecture.web.api

import android.content.Context
import com.kazakago.cleanarchitecture.web.parser.MoshiBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitBuilder(context: Context, baseUrl: String) {

    private val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(OkHttpClient.Builder().addInterceptor(ChuckInterceptor(context)).build())
            .addConverterFactory(MoshiConverterFactory.create(MoshiBuilder().build()))

    fun build(): Retrofit {
        return builder.build()
    }

}