package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.database.entity.weather.WeatherEntity
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.distributor.Stored
import java.net.URL
import java.time.LocalDateTime

internal class WeatherEntityMapper(
    private val locationEntityMapper: LocationEntityMapper,
    private val descriptionEntityMapper: DescriptionEntityMapper,
    private val forecastEntityMapper: ForecastEntityMapper
) {

    fun map(weather: WeatherEntity, location: LocationEntity, description: DescriptionEntity, forecasts: List<ForecastEntity>): Stored<Weather> {
        return Stored(
            value = Weather(
                cityId = CityId(weather.cityId),
                location = locationEntityMapper.map(location),
                title = weather.title,
                link = URL(weather.link),
                publicTime = LocalDateTime.parse(weather.publicTime),
                description = descriptionEntityMapper.map(description),
                forecasts = forecasts.map { forecastEntityMapper.map(it) }
            ),
            storedTime = LocalDateTime.parse(weather.storedTime)
        )
    }

    fun reverse(destination: Stored<Weather>): ReverseMappingResult {
        return ReverseMappingResult(
            weatherEntity = WeatherEntity(
                cityId = destination.value.cityId.value,
                title = destination.value.title,
                link = destination.value.link.toString(),
                publicTime = destination.value.publicTime.toString(),
                storedTime = destination.storedTime.toString()
            ),
            locationEntity = locationEntityMapper.reverse(destination.value.cityId, destination.value.location),
            descriptionEntity = descriptionEntityMapper.reverse(destination.value.cityId, destination.value.description),
            forecastEntities = destination.value.forecasts.map { forecastEntityMapper.reverse(destination.value.cityId, it) }
        )
    }

    data class ReverseMappingResult(
        val weatherEntity: WeatherEntity,
        val locationEntity: LocationEntity,
        val descriptionEntity: DescriptionEntity,
        val forecastEntities: List<ForecastEntity>
    )

}
