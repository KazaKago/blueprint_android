package com.kazakago.cleanarchitecture.domain.repository.weather

import com.kazakago.cleanarchitecture.domain.model.weather.Weather

import io.reactivex.Single

interface WeatherApiRepository {

    fun fetch(cityId: String): Single<Weather>

}
