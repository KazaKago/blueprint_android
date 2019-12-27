package com.kazakago.cleanarchitecture.repository.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.api.api.weather.WeatherApi
import com.kazakago.cleanarchitecture.database.dao.AppDatabase
import com.kazakago.cleanarchitecture.memory.memory.MemoryState
import com.kazakago.cleanarchitecture.memory.memory.weather.WeatherMemory
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreDistributor
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.mapper.city.CityIdEntityMapper
import com.kazakago.cleanarchitecture.repository.mapper.weather.*
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.Duration

internal class WeatherRepositoryImpl(context: Context) : WeatherRepository {

    private val weatherApi = WeatherApi(context)
    private val weatherDao = AppDatabase.create(context).weatherDao()
    private val cityIdEntityMapper = CityIdEntityMapper()
    private val weatherResponseMapper = WeatherResponseMapper(LocationResponseMapper(), DescriptionResponseMapper(), ForecastResponseMapper())
    private val weatherStateMapper = WeatherStateMapper(WeatherEntityMapper(LocationEntityMapper(), DescriptionEntityMapper(), ForecastEntityMapper()))

    override suspend fun subscribe(cityId: CityId, expired: Duration): Flow<StoreState<Weather>> {
        return find(cityId)
            .onStart {
                CoroutineScope(Dispatchers.IO).launch { distribute(cityId, expired) }
            }
    }

    private suspend fun distribute(cityId: CityId, expired: Duration) {
        StoreDistributor(expired).execute(
            load = { find(cityId).first() },
            fetch = { fetch(cityId) },
            save = { insert(cityId, it) }
        )
    }

    private suspend fun fetch(cityId: CityId): Weather {
        val weatherResponse = weatherApi.fetch(cityId.value)
        return weatherResponseMapper.map(weatherResponse, cityId)
    }

    private suspend fun find(cityId: CityId): Flow<StoreState<Weather>> {
        return WeatherMemory.weatherState.asFlow()
            .map {
                it.getOrElse(cityIdEntityMapper.reverse(cityId)) { MemoryState.Fixed }
            }
            .map {
                val weather = weatherDao.findWeather(cityId.value)
                val location = weatherDao.findLocation(cityId.value)
                val description = weatherDao.findDescription(cityId.value)
                val forecasts = weatherDao.findForecasts(cityId.value)
                weatherStateMapper.map(it, weather, location, description, forecasts)
            }
    }

    private suspend fun insert(cityId: CityId, weather: StoreState<Weather>) {
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
