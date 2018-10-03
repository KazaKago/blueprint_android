package com.kazakago.cleanarchitecture.web.api.weather

import android.content.Context
import com.kazakago.cleanarchitecture.web.api.RetrofitBuilder
import com.kazakago.cleanarchitecture.web.response.entity.weather.WeatherResponse
import retrofit2.HttpException
import java.net.URL

class WeatherApi(context: Context) {

    private val apiService = RetrofitBuilder(context, URL("http://weather.livedoor.com/forecast/webservice/"))
            .build()
            .create(WeatherService::class.java)!!

    fun fetch(cityId: String): WeatherResponse {
        val weatherApiResponse = apiService.fetch(cityId).execute()
        if (weatherApiResponse.isSuccessful) {
            return weatherApiResponse.body()!!
        } else {
            throw HttpException(weatherApiResponse)
        }
    }

}
