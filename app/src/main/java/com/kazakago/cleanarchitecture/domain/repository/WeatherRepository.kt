package com.kazakago.cleanarchitecture.domain.repository

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel

import io.reactivex.Single

/**
 * Weather Repository
 *
 * @author Kensuke
 */
interface WeatherRepository {

    fun fetch(cityId: String): Single<WeatherModel>

    fun find(cityId: String): WeatherModel?

    fun exist(cityId: String): Boolean

    fun insert(weather: WeatherModel)

    fun delete(cityId: String)

}
