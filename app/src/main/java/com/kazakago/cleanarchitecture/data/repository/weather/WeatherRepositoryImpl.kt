package com.kazakago.cleanarchitecture.data.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.data.database.dao.AppDatabase
import com.kazakago.cleanarchitecture.data.database.mapper.weather.WeatherMapper
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository

class WeatherRepositoryImpl(private val context: Context) : WeatherRepository {

    private val database = AppDatabase.create(context)

    override fun find(cityId: String): Weather? {
        val weatherDao = database.weatherDao()
        return weatherDao.findWeather(cityId)?.let {
            WeatherMapper.map(it)
        }
    }

    override fun insert(weather: Weather) {
        val weatherEntity = WeatherMapper.reverse(weather)
        val weatherDao = database.weatherDao()
        weatherDao.insert(weatherEntity)
    }

    override fun delete(weather: Weather) {
        val weatherEntity = WeatherMapper.reverse(weather)
        val weatherDao = database.weatherDao()
        weatherDao.delete(weatherEntity)
    }

}
