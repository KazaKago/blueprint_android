package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.api.entity.weather.WeatherResponse
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.extension.parseDateTime
import com.kazakago.cleanarchitecture.repository.mapper.ResponseMapper
import java.net.URL

object WeatherResponseMapper : ResponseMapper<WeatherResponse, Weather> {

    override fun map(source: WeatherResponse): Weather {
        return Weather(
            location = LocationResponseMapper.map(source.location),
            title = source.title,
            link = URL(source.link),
            publicTime = source.publicTime.parseDateTime(),
            description = DescriptionResponseMapper.map(source.description),
            forecasts = ForecastResponseMapper.map(source.forecasts)
        )
    }

}
