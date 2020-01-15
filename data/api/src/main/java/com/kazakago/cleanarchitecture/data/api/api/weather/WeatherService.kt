package com.kazakago.cleanarchitecture.data.api.api.weather

import com.kazakago.cleanarchitecture.data.api.entity.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherService {

    @GET("json/v1")
    suspend fun fetch(@Query("city") cityId: String): Response<WeatherResponse>

}
