package com.kazakago.cleanarchitecture.repository.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather

interface WeatherRepository {

    suspend fun find(cityId: CityId): Weather

    suspend fun insert(weather: Weather)

    suspend fun delete(weather: Weather)

}
