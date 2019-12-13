package com.kazakago.cleanarchitecture.repository.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.api.api.weather.WeatherApi
import com.kazakago.cleanarchitecture.database.dao.AppDatabase
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.distributor.Distributor
import com.kazakago.cleanarchitecture.repository.distributor.Stored
import com.kazakago.cleanarchitecture.repository.mapper.weather.*
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import java.time.Duration

internal class WeatherRepositoryImpl(context: Context) : WeatherRepository {

    private val weatherApi = WeatherApi(context)
    private val weatherDao = AppDatabase.create(context).weatherDao()
    private val weatherResponseMapper = WeatherResponseMapper(LocationResponseMapper(), DescriptionResponseMapper(), ForecastResponseMapper())
    private val weatherEntityMapper = WeatherEntityMapper(LocationEntityMapper(), DescriptionEntityMapper(), ForecastEntityMapper())

    override suspend fun get(cityId: CityId, expired: Duration): Weather {
        return Distributor(expired).execute(
            load = { find(cityId) },
            fetch = { fetch(cityId) },
            save = { insert(it) }
        )
    }

    private suspend fun fetch(cityId: CityId): Weather {
        val weatherResponse = weatherApi.fetch(cityId.value)
        return weatherResponseMapper.map(weatherResponse, cityId)
    }

    private suspend fun find(cityId: CityId): Stored<Weather>? {
        return weatherDao.findWeather(cityId.value)?.let {
            val location = weatherDao.findLocation(cityId.value)!!
            val description = weatherDao.findDescription(cityId.value)!!
            val forecasts = weatherDao.findForecasts(cityId.value)
            return weatherEntityMapper.map(it, location, description, forecasts)
        }
    }

    private suspend fun insert(weather: Stored<Weather>) {
        val reverseMappingResult = weatherEntityMapper.reverse(weather)
        weatherDao.insert(reverseMappingResult.weatherEntity)
        weatherDao.insert(reverseMappingResult.locationEntity)
        weatherDao.insert(reverseMappingResult.descriptionEntity)
        weatherDao.insert(reverseMappingResult.forecastEntities)
    }

}
