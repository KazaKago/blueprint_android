package com.kazakago.cleanarchitecture.repository.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.api.api.weather.WeatherApi
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.mapper.weather.WeatherResponseMapper
import com.kazakago.cleanarchitecture.repository.weather.WeatherApiRepository

internal class WeatherApiRepositoryImpl(private val context: Context) : WeatherApiRepository {

    override suspend fun fetch(cityId: CityId): Weather {
        val weatherResponse = WeatherApi(context).fetch(cityId.value)
        return WeatherResponseMapper.map(weatherResponse)
    }

}
