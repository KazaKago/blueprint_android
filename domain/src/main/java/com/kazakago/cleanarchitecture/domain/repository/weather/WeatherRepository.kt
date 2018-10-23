package com.kazakago.cleanarchitecture.domain.repository.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather

interface WeatherRepository {

    suspend fun find(cityId: CityId): Weather

    suspend fun insert(weather: Weather)

    suspend fun delete(weather: Weather)

}
