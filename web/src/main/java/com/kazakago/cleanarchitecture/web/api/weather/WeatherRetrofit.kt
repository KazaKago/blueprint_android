package com.kazakago.cleanarchitecture.web.api.weather

import com.kazakago.cleanarchitecture.web.api.RetrofitBuilder

object WeatherRetrofit {

    fun getWeatherApi(): WeatherApi {
        return RetrofitBuilder("http://weather.livedoor.com/forecast/webservice/")
                .build()
                .create(WeatherApi::class.java)
    }

}
