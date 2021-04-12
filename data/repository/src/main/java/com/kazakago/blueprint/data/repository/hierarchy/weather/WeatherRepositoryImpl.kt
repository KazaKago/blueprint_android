package com.kazakago.blueprint.data.repository.hierarchy.weather

import android.content.Context
import com.kazakago.blueprint.data.api.hierarchy.weather.WeatherApi
import com.kazakago.blueprint.data.database.entity.weather.DescriptionEntity
import com.kazakago.blueprint.data.database.entity.weather.ForecastEntity
import com.kazakago.blueprint.data.database.entity.weather.LocationEntity
import com.kazakago.blueprint.data.database.entity.weather.WeatherEntity
import com.kazakago.blueprint.data.database.global.AppDatabase
import com.kazakago.blueprint.data.repository.global.dispatcher.CacheFlowDispatcher
import com.kazakago.blueprint.data.repository.mapper.weather.*
import com.kazakago.blueprint.domain.model.global.state.State
import com.kazakago.blueprint.domain.model.global.state.mapContent
import com.kazakago.blueprint.domain.model.hierarchy.city.CityId
import com.kazakago.blueprint.domain.model.hierarchy.weather.Weather
import com.kazakago.blueprint.domain.repository.hierarchy.weather.WeatherRepository
import com.os.operando.guild.kt.Quartet
import com.os.operando.guild.kt.to
import kotlinx.coroutines.flow.Flow

internal class WeatherRepositoryImpl(context: Context) : WeatherRepository {

    private val weatherApi = WeatherApi(context)
    private val weatherDao = AppDatabase.create(context).weatherDao()
    private val weatherResponseMapper = WeatherResponseMapper(LocationResponseMapper(), DescriptionResponseMapper(), ForecastResponseMapper())
    private val weatherEntityMapper = WeatherEntityMapper(LocationEntityMapper(), DescriptionEntityMapper(), ForecastEntityMapper())

    override fun subscribe(cityId: CityId): Flow<State<Weather>> {
        return getDispatcher(cityId)
            .subscribe(needRefresh = { it.first.isExpired() })
            .mapContent {
                weatherEntityMapper.map(it.first, it.second, it.third, it.fourth)
            }
    }

    override suspend fun request(cityId: CityId) {
        return getDispatcher(cityId).request()
    }

    private fun getDispatcher(cityId: CityId): CacheFlowDispatcher<Quartet<WeatherEntity, LocationEntity, DescriptionEntity, List<ForecastEntity>>> {
        return CacheFlowDispatcher(
            stateId = WeatherEntity::class.qualifiedName + cityId.value,
            loadEntity = { loadEntity(cityId) },
            fetchOrigin = {
                val fetched = weatherApi.fetch(cityId.value)
                val model = weatherResponseMapper.map(fetched, cityId)
                weatherEntityMapper.reverse(model)
            },
            saveEntity = { saveEntity(it.first, it.second, it.third, it.fourth) }
        )
    }

    private suspend fun loadEntity(cityId: CityId): Quartet<WeatherEntity, LocationEntity, DescriptionEntity, List<ForecastEntity>>? {
        val weather = weatherDao.findWeather(cityId.value)
        val location = weatherDao.findLocation(cityId.value)
        val description = weatherDao.findDescription(cityId.value)
        val forecasts = weatherDao.findForecasts(cityId.value)
        return if (weather != null && location != null && description != null && forecasts != null) {
            return weather to location to description to forecasts
        } else {
            null
        }
    }

    private suspend fun saveEntity(weather: WeatherEntity, location: LocationEntity, description: DescriptionEntity, forecasts: List<ForecastEntity>) {
        weatherDao.insertWeather(weather)
        weatherDao.insertLocation(location)
        weatherDao.insertDescription(description)
        weatherDao.insertForecasts(forecasts)
    }
}
