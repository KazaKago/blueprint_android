package com.kazakago.cleanarchitecture.domain.repository.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather

interface WeatherApiRepository {

    suspend fun fetch(cityId: CityId): Weather

}
