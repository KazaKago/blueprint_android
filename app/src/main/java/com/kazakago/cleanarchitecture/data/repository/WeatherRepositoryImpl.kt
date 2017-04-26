package com.kazakago.cleanarchitecture.data.repository

import com.kazakago.cleanarchitecture.data.api.WeatherApi
import com.kazakago.cleanarchitecture.data.api.WeatherRetrofit
import com.kazakago.cleanarchitecture.data.dao.WeatherDao
import com.kazakago.cleanarchitecture.data.mapper.WeatherMapper
import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository
import io.reactivex.Single
import io.realm.Realm

/**
 * Weather Repository Implement
 *
 * Created by tamura_k on 2016/05/27.
 */
class WeatherRepositoryImpl : WeatherRepository {

    override fun fetch(cityId: String): Single<WeatherModel> {
        val weatherApi = WeatherRetrofit.instance.create(WeatherApi::class.java)
        return weatherApi[cityId]
                .map {
                    WeatherMapper.execute(it)
                }
    }

    override fun find(cityId: String): WeatherModel? {
        Realm.getDefaultInstance().use {
            val weatherDao = WeatherDao(it)
            return weatherDao.find(cityId)?.let {
                WeatherMapper.execute(it)
            }
        }
    }

    override fun exist(cityId: String): Boolean {
        Realm.getDefaultInstance().use {
            val weatherDao = WeatherDao(it)
            return weatherDao.exist(cityId)
        }
    }

    override fun insert(weather: WeatherModel) {
        val weatherEntity = WeatherMapper.execute(weather)
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            val weatherDao = WeatherDao(it)
            weatherDao.insert(weatherEntity)
            it.commitTransaction()
        }
    }

    override fun delete(cityId: String) {
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            val weatherDao = WeatherDao(it)
            weatherDao.delete(cityId)
            it.commitTransaction()
        }
    }

}
