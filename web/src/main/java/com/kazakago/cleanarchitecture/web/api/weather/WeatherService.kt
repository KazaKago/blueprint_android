package com.kazakago.cleanarchitecture.web.api.weather

import com.kazakago.cleanarchitecture.web.response.entity.weather.WeatherResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("json/v1")
    fun fetch(@Query("city") cityId: String): Deferred<WeatherResponse>

}
