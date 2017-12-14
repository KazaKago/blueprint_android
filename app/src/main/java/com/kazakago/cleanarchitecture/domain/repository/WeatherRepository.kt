package com.kazakago.cleanarchitecture.domain.repository

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel

interface WeatherRepository {

    fun find(cityId: String): WeatherModel?

    fun exist(cityId: String): Boolean

    fun insert(weather: WeatherModel)

    fun delete(weather: WeatherModel)

}
