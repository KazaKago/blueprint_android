package com.kazakago.cleanarchitecture.repository.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather

interface WeatherApiRepository {

    suspend fun fetch(cityId: CityId): Weather

}
