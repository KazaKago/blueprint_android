package com.kazakago.cleanarchitecture.data.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.data.database.dao.AppDatabase
import com.kazakago.cleanarchitecture.data.database.mapper.weather.WeatherEntityMapper
import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.Singles

class WeatherRepositoryImpl(private val context: Context) : WeatherRepository {

    private val database = AppDatabase.create(context)

    override fun find(cityId: CityId): Single<Weather> {
        val weatherDao = database.weatherDao()
        return Singles.zip(weatherDao.findWeather(cityId.value),
                weatherDao.findLocation(cityId.value),
                weatherDao.findDescription(cityId.value),
                weatherDao.findForecasts(cityId.value),
                { weather, location, description, forecasts ->
                    WeatherEntityMapper.map(weather, location, description, forecasts)
                })
    }

    override fun insert(weather: Weather) {
        val reverseMappingResult = WeatherEntityMapper.reverse(weather)
        val weatherDao = database.weatherDao()
        weatherDao.insert(reverseMappingResult.weatherEntity)
        weatherDao.insert(reverseMappingResult.locationEntity)
        weatherDao.insert(reverseMappingResult.descriptionEntity)
        weatherDao.insert(reverseMappingResult.forecastEntities)
    }

    override fun delete(weather: Weather) {
        val reverseMappingResult = WeatherEntityMapper.reverse(weather)
        val weatherDao = database.weatherDao()
        weatherDao.delete(reverseMappingResult.weatherEntity)
        weatherDao.delete(reverseMappingResult.locationEntity)
        weatherDao.delete(reverseMappingResult.descriptionEntity)
        weatherDao.delete(reverseMappingResult.forecastEntities)
    }

}
