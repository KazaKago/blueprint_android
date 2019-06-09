package com.kazakago.cleanarchitecture.web.api.weather

import com.kazakago.cleanarchitecture.web.response.entity.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("json/v1")
    suspend fun fetch(@Query("city") cityId: String): Response<WeatherResponse>

}
