package com.kazakago.cleanarchitecture.repository.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.database.dao.AppDatabase
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.mapper.weather.WeatherEntityMapper
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository

internal class WeatherRepositoryImpl(private val context: Context) : WeatherRepository {

    private val database = AppDatabase.create(context)

    override suspend fun find(cityId: CityId): Weather {
        val weatherDao = database.weatherDao()
        val weather = weatherDao.findWeather(cityId.value)!!
        val location = weatherDao.findLocation(cityId.value)!!
        val description = weatherDao.findDescription(cityId.value)!!
        val forecasts = weatherDao.findForecasts(cityId.value)
        return WeatherEntityMapper.map(weather, location, description, forecasts)
    }

    override suspend fun insert(weather: Weather) {
        val weatherDao = database.weatherDao()
        val reverseMappingResult = WeatherEntityMapper.reverse(weather)
        weatherDao.insert(reverseMappingResult.weatherEntity)
        weatherDao.insert(reverseMappingResult.locationEntity)
        weatherDao.insert(reverseMappingResult.descriptionEntity)
        weatherDao.insert(reverseMappingResult.forecastEntities)
    }

    override suspend fun delete(weather: Weather) {
        val weatherDao = database.weatherDao()
        val reverseMappingResult = WeatherEntityMapper.reverse(weather)
        weatherDao.delete(reverseMappingResult.weatherEntity)
    }

}
