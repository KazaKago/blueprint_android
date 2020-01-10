package com.kazakago.cleanarchitecture.repository.distributor.weather

import android.content.Context
import com.kazakago.cleanarchitecture.api.api.weather.WeatherApi
import com.kazakago.cleanarchitecture.database.dao.AppDatabase
import com.kazakago.cleanarchitecture.memory.memory.MemoryState
import com.kazakago.cleanarchitecture.memory.memory.weather.WeatherMemory
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.mapper.city.CityIdEntityMapper
import com.kazakago.cleanarchitecture.repository.mapper.weather.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map

internal class WeatherDistributor(context: Context) {

    private val weatherApi = WeatherApi(context)
    private val weatherDao = AppDatabase.create(context).weatherDao()
    private val cityIdEntityMapper = CityIdEntityMapper()
    private val weatherResponseMapper = WeatherResponseMapper(LocationResponseMapper(), DescriptionResponseMapper(), ForecastResponseMapper())
    private val weatherStateMapper = WeatherStateMapper(WeatherEntityMapper(LocationEntityMapper(), DescriptionEntityMapper(), ForecastEntityMapper()))

    fun subscribe(cityId: CityId): Flow<StoreState<Weather>> {
        return WeatherMemory.weatherState.asFlow()
            .map {
                it.getOrDefault(cityIdEntityMapper.reverse(cityId), MemoryState.Fixed)
            }
            .map {
                val weather = weatherDao.findWeather(cityId.value)
                val location = weatherDao.findLocation(cityId.value)
                val description = weatherDao.findDescription(cityId.value)
                val forecasts = weatherDao.findForecasts(cityId.value)
                weatherStateMapper.map(it, weather, location, description, forecasts)
            }
    }

    suspend fun fetch(cityId: CityId): Weather {
        val weatherResponse = weatherApi.fetch(cityId.value)
        return weatherResponseMapper.map(weatherResponse, cityId)
    }

    suspend fun save(cityId: CityId, weather: StoreState<Weather>) {
        val reverseMappingResult = weatherStateMapper.reverse(weather)
        if (reverseMappingResult.weatherEntity != null && reverseMappingResult.locationEntity != null && reverseMappingResult.descriptionEntity != null && reverseMappingResult.forecastEntities != null) {
            weatherDao.insertWeather(reverseMappingResult.weatherEntity)
            weatherDao.insertLocation(reverseMappingResult.locationEntity)
            weatherDao.insertDescription(reverseMappingResult.descriptionEntity)
            weatherDao.insertForecasts(reverseMappingResult.forecastEntities)
        } else {
            weatherDao.findWeather(cityId.value)?.let { weatherDao.deleteWeather(it) }
        }
        val weatherMap = WeatherMemory.weatherState.value.toMutableMap()
        weatherMap[cityIdEntityMapper.reverse(cityId)] = reverseMappingResult.memoryState
        WeatherMemory.weatherState.send(weatherMap)
    }

}