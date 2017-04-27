package com.kazakago.cleanarchitecture.domain.repository

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel

import io.reactivex.Single

/**
 * Weather API Repository
 *
 * @author Kensuke
 */
interface WeatherApiRepository {

    fun fetch(cityId: String): Single<WeatherModel>

}
