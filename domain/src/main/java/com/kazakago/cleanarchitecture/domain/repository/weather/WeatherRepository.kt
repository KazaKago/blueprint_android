package com.kazakago.cleanarchitecture.domain.repository.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import io.reactivex.Single

interface WeatherRepository {

    fun find(cityId: CityId): Single<Weather>

    fun insert(weather: Weather)

    fun delete(weather: Weather)

}
