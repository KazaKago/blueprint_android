package com.kazakago.cleanarchitecture.web.api.weather

import android.content.Context
import com.kazakago.cleanarchitecture.web.api.RetrofitBuilder

object WeatherRetrofit {

    fun getWeatherApi(context: Context): WeatherApi {
        return RetrofitBuilder(context, "http://weather.livedoor.com/forecast/webservice/")
                .build()
                .create(WeatherApi::class.java)
    }

}
