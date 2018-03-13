package com.kazakago.cleanarchitecture.web.repository.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.web.api.weather.WeatherRetrofit
import com.kazakago.cleanarchitecture.web.response.mapper.weather.WeatherResponseMapper
import io.reactivex.Single

class WeatherApiRepositoryImpl : WeatherApiRepository {

    override fun fetch(cityId: CityId): Single<Weather> {
        val weatherApi = WeatherRetrofit.getWeatherApi()
        return weatherApi[cityId.value].map {
            WeatherResponseMapper.map(it)
        }
    }

}