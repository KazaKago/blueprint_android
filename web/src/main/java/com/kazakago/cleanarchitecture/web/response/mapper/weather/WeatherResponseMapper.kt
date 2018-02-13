package com.kazakago.cleanarchitecture.web.response.mapper.weather

import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.web.extension.parseDateTime
import com.kazakago.cleanarchitecture.web.response.entity.weather.WeatherResponse
import com.kazakago.cleanarchitecture.web.response.mapper.ResponseMapper
import java.net.URL

object WeatherResponseMapper : ResponseMapper<WeatherResponse, Weather> {

    override fun map(source: WeatherResponse): Weather {
        return Weather(
                location = LocationResponseMapper.map(source.location),
                title = source.title,
                link = URL(source.link),
                publicTime = source.publicTime.parseDateTime(),
                description = DescriptionResponseMapper.map(source.description),
                forecasts = source.forecasts.map { ForecastResponseMapper.map(it) })
    }

}
