package com.kazakago.cleanarchitecture.data.api

import com.kazakago.cleanarchitecture.data.entity.weather.WeatherEntity

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Weather API from livedoor
 * http://weather.livedoor.com/weather_hacks/webservice
 *
 * Created by tamura_k on 2016/06/03.
 */
interface WeatherApi {

    @GET("json/v1")
    operator fun get(@Query("city") cityId: String): Single<WeatherEntity>

}
