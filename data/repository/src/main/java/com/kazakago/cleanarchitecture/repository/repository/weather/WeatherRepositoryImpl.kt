package com.kazakago.cleanarchitecture.repository.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.api.api.weather.WeatherApi
import com.kazakago.cleanarchitecture.database.dao.AppDatabase
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.distributor.Distributor
import com.kazakago.cleanarchitecture.repository.distributor.Stored
import com.kazakago.cleanarchitecture.repository.mapper.weather.WeatherEntityMapper
import com.kazakago.cleanarchitecture.repository.mapper.weather.WeatherResponseMapper
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import java.time.Duration

internal class WeatherRepositoryImpl(private val context: Context) : WeatherRepository {

    private val database = AppDatabase.create(context)

    override suspend fun get(cityId: CityId, expired: Duration): Weather {
        return Distributor(expired).execute(
            load = { find(cityId) },
            fetch = { fetch(cityId) },
            save = { insert(it) }
        )
    }

    private suspend fun fetch(cityId: CityId): Weather {
        val weatherResponse = WeatherApi(context).fetch(cityId.value)
        return WeatherResponseMapper.map(weatherResponse, cityId)
    }

    private suspend fun find(cityId: CityId): Stored<Weather>? {
        val weatherDao = database.weatherDao()
        return weatherDao.findWeather(cityId.value)?.let {
            val location = weatherDao.findLocation(cityId.value)!!
            val description = weatherDao.findDescription(cityId.value)!!
            val forecasts = weatherDao.findForecasts(cityId.value)
            return WeatherEntityMapper.map(it, location, description, forecasts)
        }
    }

    private suspend fun insert(weather: Stored<Weather>) {
        val weatherDao = database.weatherDao()
        val reverseMappingResult = WeatherEntityMapper.reverse(weather)
        weatherDao.insert(reverseMappingResult.weatherEntity)
        weatherDao.insert(reverseMappingResult.locationEntity)
        weatherDao.insert(reverseMappingResult.descriptionEntity)
        weatherDao.insert(reverseMappingResult.forecastEntities)
    }

}
