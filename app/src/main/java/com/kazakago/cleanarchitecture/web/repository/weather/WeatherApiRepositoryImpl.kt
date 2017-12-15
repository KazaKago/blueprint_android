package com.kazakago.cleanarchitecture.web.repository.weather

import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.web.api.weather.WeatherApi
import com.kazakago.cleanarchitecture.web.api.weather.WeatherRetrofit
import com.kazakago.cleanarchitecture.web.mapper.weather.WeatherApiMapper
import io.reactivex.Single

class WeatherApiRepositoryImpl : WeatherApiRepository {

    override fun fetch(cityId: String): Single<Weather> {
        val weatherApi = WeatherRetrofit.instance.create(WeatherApi::class.java)
        return weatherApi[cityId].map {
            WeatherApiMapper.map(it)
        }
    }

}
