package com.kazakago.cleanarchitecture.repository.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather
import java.time.Duration

interface WeatherRepository {

    suspend fun get(cityId: CityId, expired: Duration): Weather

}
