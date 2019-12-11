package com.kazakago.cleanarchitecture.repository.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather

interface WeatherRepository {

    suspend fun get(cityId: CityId): Weather

}
