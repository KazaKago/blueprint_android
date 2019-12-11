package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.database.entity.weather.WeatherEntity
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather
import java.net.URL
import java.util.*

object WeatherEntityMapper {

    fun map(weather: WeatherEntity, location: LocationEntity, description: DescriptionEntity, forecasts: List<ForecastEntity>): Weather {
        return Weather(
            cityId = CityId(weather.cityId),
            location = LocationEntityMapper.map(location),
            title = weather.title,
            link = URL(weather.link),
            publicTime = Date(weather.publicTime),
            description = DescriptionEntityMapper.map(description),
            forecasts = forecasts.map { ForecastEntityMapper.map(it) }
        )
    }

    fun reverse(destination: Weather): ReverseMappingResult {
        return ReverseMappingResult(
            weatherEntity = WeatherEntity(
                cityId = destination.cityId.value,
                title = destination.title,
                link = destination.link.toString(),
                publicTime = destination.publicTime.time
            ),
            locationEntity = LocationEntityMapper.reverse(destination.cityId, destination.location),
            descriptionEntity = DescriptionEntityMapper.reverse(destination.cityId, destination.description),
            forecastEntities = destination.forecasts.map { ForecastEntityMapper.reverse(destination.cityId, it) })
    }

    data class ReverseMappingResult(
        val weatherEntity: WeatherEntity,
        val locationEntity: LocationEntity,
        val descriptionEntity: DescriptionEntity,
        val forecastEntities: List<ForecastEntity>
    )

}
