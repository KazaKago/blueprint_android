package com.kazakago.cleanarchitecture.data.repository

import com.kazakago.cleanarchitecture.data.dao.WeatherDao
import com.kazakago.cleanarchitecture.data.mapper.weather.WeatherMapper
import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository
import io.realm.Realm

/**
 * Weather Repository Implement
 *
 * Created by tamura_k on 2016/05/27.
 */
class WeatherRepositoryImpl : WeatherRepository {

    override fun find(cityId: String): WeatherModel? {
        Realm.getDefaultInstance().use {
            val weatherDao = WeatherDao(realm = it)
            return weatherDao.find(cityId = cityId)?.let { WeatherMapper.map(source = it) }
        }
    }

    override fun exist(cityId: String): Boolean {
        Realm.getDefaultInstance().use {
            val weatherDao = WeatherDao(realm = it)
            return weatherDao.exist(cityId = cityId)
        }
    }

    override fun insert(weather: WeatherModel) {
        val weatherEntity = WeatherMapper.reverse(weather)
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            val weatherDao = WeatherDao(realm = it)
            weatherDao.insert(weather = weatherEntity)
            it.commitTransaction()
        }
    }

    override fun delete(cityId: String) {
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            val weatherDao = WeatherDao(realm = it)
            weatherDao.delete(cityId = cityId)
            it.commitTransaction()
        }
    }

}
