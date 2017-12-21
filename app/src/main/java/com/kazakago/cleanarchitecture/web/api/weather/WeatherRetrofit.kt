package com.kazakago.cleanarchitecture.web.api.weather

import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object WeatherRetrofit {

    private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    private val retrofit = Retrofit.Builder()
            .baseUrl("http://weather.livedoor.com/forecast/webservice/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun getWeatherApi(): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

}
