package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.api.entity.weather.WeatherResponse
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.extension.parseDateTime
import java.net.URL

internal object WeatherResponseMapper {

    fun map(response: WeatherResponse, cityId: CityId): Weather {
        return Weather(
            cityId = cityId,
            location = LocationResponseMapper.map(response.location),
            title = response.title,
            link = URL(response.link),
            publicTime = response.publicTime.parseDateTime(),
            description = DescriptionResponseMapper.map(response.description),
            forecasts = response.forecasts.map { ForecastResponseMapper.map(it) }
        )
    }

}
