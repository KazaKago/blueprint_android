package com.kazakago.cleanarchitecture.web.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.web.api.weather.WeatherApi
import com.kazakago.cleanarchitecture.web.response.mapper.weather.WeatherResponseMapper

internal class WeatherApiRepositoryImpl(private val context: Context) : WeatherApiRepository {

    override suspend fun fetch(cityId: CityId): Weather {
        val weatherResponse = WeatherApi(context).fetch(cityId.value)
        return WeatherResponseMapper.map(weatherResponse)
    }

}
