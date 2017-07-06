package com.kazakago.cleanarchitecture.web.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * WeatherRetrofit from livedoor
 *
 * Created by tamura_k on 2016/06/03.
 */
object WeatherRetrofit {

    val instance: Retrofit
        get() = Retrofit.Builder()
                .baseUrl("http://weather.livedoor.com/forecast/webservice/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
}
