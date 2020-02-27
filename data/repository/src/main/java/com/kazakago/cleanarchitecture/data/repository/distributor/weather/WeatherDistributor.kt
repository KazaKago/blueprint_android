package com.kazakago.cleanarchitecture.data.repository.distributor.weather

import android.content.Context
import com.kazakago.cleanarchitecture.data.api.hierarchy.weather.WeatherApi
import com.kazakago.cleanarchitecture.data.database.global.AppDatabase
import com.kazakago.cleanarchitecture.data.memory.global.DataState
import com.kazakago.cleanarchitecture.data.memory.global.getOrPut
import com.kazakago.cleanarchitecture.data.memory.hierarchy.weather.WeatherMemory
import com.kazakago.cleanarchitecture.data.repository.mapper.city.CityIdEntityMapper
import com.kazakago.cleanarchitecture.data.repository.mapper.weather.*
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Weather
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map

internal class WeatherDistributor(context: Context) {

    private val weatherApi = WeatherApi(context)
    private val weatherDao = AppDatabase.create(context).weatherDao()
    private val cityIdEntityMapper = CityIdEntityMapper()
    private val weatherResponseMapper = WeatherResponseMapper(LocationResponseMapper(), DescriptionResponseMapper(), ForecastResponseMapper())
    private val weatherEntityMapper = WeatherEntityMapper(LocationEntityMapper(), DescriptionEntityMapper(), ForecastEntityMapper())

    fun subscribeState(cityId: CityId): Flow<DataState> {
        return WeatherMemory.weatherState.getOrPut(cityIdEntityMapper.reverse(cityId)).asFlow()
    }

    suspend fun saveState(state: DataState, cityId: CityId) {
        WeatherMemory.weatherState.getOrPut(cityIdEntityMapper.reverse(cityId)).send(state)
    }

    suspend fun loadContent(cityId: CityId): Weather? {
        val weather = weatherDao.findWeather(cityId.value)
        val location = weatherDao.findLocation(cityId.value)
        val description = weatherDao.findDescription(cityId.value)
        val forecasts = weatherDao.findForecasts(cityId.value)
        return if (weather != null && location != null && description != null && forecasts != null) {
            weatherEntityMapper.map(weather, location, description, forecasts)
        } else {
            null
        }
    }

    suspend fun fetchContent(cityId: CityId): Weather {
        val weatherResponse = weatherApi.fetch(cityId.value)
        return weatherResponseMapper.map(weatherResponse, cityId)
    }

    suspend fun saveContent(weather: Weather) {
        val reverseMappingResult = weatherEntityMapper.reverse(weather)
        weatherDao.insertWeather(reverseMappingResult.weatherEntity)
        weatherDao.insertLocation(reverseMappingResult.locationEntity)
        weatherDao.insertDescription(reverseMappingResult.descriptionEntity)
        weatherDao.insertForecasts(reverseMappingResult.forecastEntities)
    }

}
