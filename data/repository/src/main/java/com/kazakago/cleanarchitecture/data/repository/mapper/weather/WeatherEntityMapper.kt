package com.kazakago.cleanarchitecture.data.repository.mapper.weather

import com.kazakago.cleanarchitecture.data.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.WeatherEntity
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Weather
import com.os.operando.guild.kt.Quartet
import com.os.operando.guild.kt.to
import java.net.URL
import java.time.LocalDateTime

internal class WeatherEntityMapper(
    private val locationEntityMapper: LocationEntityMapper,
    private val descriptionEntityMapper: DescriptionEntityMapper,
    private val forecastEntityMapper: ForecastEntityMapper
) {

    fun map(weather: WeatherEntity, location: LocationEntity, description: DescriptionEntity, forecasts: List<ForecastEntity>): Weather {
        return Weather(
            cityId = CityId(weather.cityId),
            location = locationEntityMapper.map(location),
            title = weather.title,
            link = URL(weather.link),
            publicTime = LocalDateTime.parse(weather.publicTime),
            description = descriptionEntityMapper.map(description),
            forecasts = forecasts.map { forecastEntityMapper.map(it) }
        )
    }

    fun reverse(destination: Weather): Quartet<WeatherEntity, LocationEntity, DescriptionEntity, List<ForecastEntity>> {
        return Quartet(
            first = WeatherEntity(
                cityId = destination.cityId.value,
                title = destination.title,
                link = destination.link.toString(),
                publicTime = destination.publicTime.toString()
            ),
            second = locationEntityMapper.reverse(destination.cityId, destination.location),
            third = descriptionEntityMapper.reverse(destination.cityId, destination.description),
            fourth = destination.forecasts.map { forecastEntityMapper.reverse(destination.cityId, it) }
        )
    }

}
