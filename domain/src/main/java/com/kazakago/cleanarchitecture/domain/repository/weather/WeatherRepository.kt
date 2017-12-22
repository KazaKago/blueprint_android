package com.kazakago.cleanarchitecture.domain.repository.weather

import com.kazakago.cleanarchitecture.domain.model.weather.Weather

interface WeatherRepository {

    fun find(cityId: String): Weather?

    fun insert(weather: Weather)

    fun delete(weather: Weather)

}
