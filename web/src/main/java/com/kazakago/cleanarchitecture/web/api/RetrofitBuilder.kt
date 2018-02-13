package com.kazakago.cleanarchitecture.web.api

import com.kazakago.cleanarchitecture.web.parser.MoshiBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitBuilder(baseUrl: String) {

    private val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(MoshiBuilder().build()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    fun build(): Retrofit {
        return builder.build()
    }

}