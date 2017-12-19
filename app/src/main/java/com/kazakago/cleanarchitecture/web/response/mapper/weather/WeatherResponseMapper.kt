package com.kazakago.cleanarchitecture.web.response.mapper.weather

import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.web.extension.parseTime
import com.kazakago.cleanarchitecture.web.response.entity.weather.WeatherResponse
import com.kazakago.cleanarchitecture.web.response.mapper.ResponseMapper

object WeatherResponseMapper : ResponseMapper<WeatherResponse, Weather> {

    override fun map(source: WeatherResponse): Weather {
        return Weather(
                location = LocationResponseMapper.map(source.location),
                title = source.title,
                link = source.link,
                publicTime = source.publicTime.parseTime(),
                description = DescriptionResponseMapper.map(source.description),
                forecasts = source.forecasts.map { ForecastResponseMapper.map(it) })
    }

}
