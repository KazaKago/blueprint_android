package com.kazakago.cleanarchitecture.data.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.data.database.dao.AppDatabase
import com.kazakago.cleanarchitecture.data.database.mapper.weather.WeatherEntityMapper
import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(private val context: Context) : WeatherRepository {

    private val database = AppDatabase.create(context)

    override suspend fun find(cityId: CityId): Weather = withContext(Dispatchers.IO) {
        val weatherDao = database.weatherDao()
        val weather = weatherDao.findWeather(cityId.value)!!
        val location = weatherDao.findLocation(cityId.value)!!
        val description = weatherDao.findDescription(cityId.value)!!
        val forecasts = weatherDao.findForecasts(cityId.value)
        WeatherEntityMapper.map(weather, location, description, forecasts)
    }

    override suspend fun insert(weather: Weather) = withContext<Unit>(Dispatchers.IO) {
        val weatherDao = database.weatherDao()
        val reverseMappingResult = WeatherEntityMapper.reverse(weather)
        weatherDao.insert(reverseMappingResult.weatherEntity)
        weatherDao.insert(reverseMappingResult.locationEntity)
        weatherDao.insert(reverseMappingResult.descriptionEntity)
        weatherDao.insert(reverseMappingResult.forecastEntities)
    }

    override suspend fun delete(weather: Weather) = withContext<Unit>(Dispatchers.IO) {
        val weatherDao = database.weatherDao()
        val reverseMappingResult = WeatherEntityMapper.reverse(weather)
        weatherDao.delete(reverseMappingResult.weatherEntity)
    }

}
