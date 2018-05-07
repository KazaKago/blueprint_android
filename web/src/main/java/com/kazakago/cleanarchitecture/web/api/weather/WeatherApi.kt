package com.kazakago.cleanarchitecture.web.api.weather

import com.kazakago.cleanarchitecture.web.response.entity.weather.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("json/v1")
    operator fun get(@Query("city") cityId: String): Call<WeatherResponse>

}
