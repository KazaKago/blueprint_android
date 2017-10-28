package com.kazakago.cleanarchitecture.web.repository

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import com.kazakago.cleanarchitecture.domain.repository.WeatherApiRepository
import com.kazakago.cleanarchitecture.web.api.WeatherApi
import com.kazakago.cleanarchitecture.web.api.WeatherRetrofit
import com.kazakago.cleanarchitecture.web.mapper.weather.WeatherApiMapper
import io.reactivex.Single

class WeatherApiRepositoryImpl : WeatherApiRepository {

    override fun fetch(cityId: String): Single<WeatherModel> {
        val weatherApi = WeatherRetrofit.instance.create(WeatherApi::class.java)
        return weatherApi[cityId].map { WeatherApiMapper.map(source = it) }
    }

}
