package com.kazakago.cleanarchitecture.data.repository.mapper.weather

import com.kazakago.cleanarchitecture.data.api.response.weather.WeatherResponse
import com.kazakago.cleanarchitecture.data.repository.global.extension.parseDateTime
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Weather
import java.net.URL

internal class WeatherResponseMapper(
    private val locationResponseMapper: LocationResponseMapper,
    private val descriptionResponseMapper: DescriptionResponseMapper,
    private val forecastResponseMapper: ForecastResponseMapper
) {

    fun map(response: WeatherResponse, cityId: CityId): Weather {
        return Weather(
            cityId = cityId,
            location = locationResponseMapper.map(response.location),
            title = response.title,
            link = URL(response.link),
            publicTime = response.publicTime.parseDateTime(),
            description = descriptionResponseMapper.map(response.description),
            forecasts = response.forecasts.map { forecastResponseMapper.map(it) }
        )
    }

}
