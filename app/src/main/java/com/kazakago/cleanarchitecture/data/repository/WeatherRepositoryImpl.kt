package com.kazakago.cleanarchitecture.data.repository

import android.arch.persistence.room.Room
import android.content.Context
import com.kazakago.cleanarchitecture.data.dao.AppDatabase
import com.kazakago.cleanarchitecture.data.mapper.weather.WeatherMapper
import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val context: Context) : WeatherRepository {

    private val database = Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()

    override fun find(cityId: String): WeatherModel? {
        val weatherDao = database.weatherDao()
        return weatherDao.find(cityId = cityId)?.let {
            WeatherMapper.map(source = it)
        }
    }

    override fun exist(cityId: String): Boolean {
        val weatherDao = database.weatherDao()
        return weatherDao.exist(cityId = cityId)
    }

    override fun insert(weather: WeatherModel) {
        val weatherEntity = WeatherMapper.reverse(weather)
        val weatherDao = database.weatherDao()
        weatherDao.insert(weather = weatherEntity)
    }

    override fun delete(weather: WeatherModel) {
        val weatherEntity = WeatherMapper.reverse(weather)
        val weatherDao = database.weatherDao()
        weatherDao.delete(weather = weatherEntity)
    }

}
