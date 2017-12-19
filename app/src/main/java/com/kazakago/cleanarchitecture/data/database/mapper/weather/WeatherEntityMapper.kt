package com.kazakago.cleanarchitecture.data.database.mapper.weather

import com.kazakago.cleanarchitecture.data.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.WeatherEntity
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import java.util.*

object WeatherEntityMapper {

    fun map(weather: WeatherEntity?, location: LocationEntity?, description: DescriptionEntity?, forecasts: List<ForecastEntity>?): Weather {
        return Weather(
                location = LocationEntityMapper.map(location),
                title = weather?.title ?: "",
                link = weather?.link ?: "",
                publicTime = Date(weather?.publicTime ?: 0),
                description = DescriptionEntityMapper.map(description),
                forecasts = forecasts?.map { ForecastEntityMapper.map(it) } ?: emptyList()
        ).apply {
            cityId = weather?.cityId ?: ""
        }
    }

    fun reverse(destination: Weather): ReverseMappingResult {
        return ReverseMappingResult(
                weatherEntity = WeatherEntity(
                        cityId = destination.cityId,
                        title = destination.title,
                        link = destination.link,
                        publicTime = destination.publicTime.time),
                locationEntity = LocationEntityMapper.reverse(destination.cityId, destination.location),
                descriptionEntity = DescriptionEntityMapper.reverse(destination.cityId, destination.description),
                forecastEntities = destination.forecasts.map { ForecastEntityMapper.reverse(destination.cityId, it) })
    }

    class ReverseMappingResult(
            val weatherEntity: WeatherEntity,
            val locationEntity: LocationEntity,
            val descriptionEntity: DescriptionEntity,
            val forecastEntities: List<ForecastEntity>)

}
