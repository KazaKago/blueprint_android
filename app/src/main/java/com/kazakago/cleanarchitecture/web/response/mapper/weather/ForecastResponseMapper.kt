package com.kazakago.cleanarchitecture.web.response.mapper.weather

import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.web.extension.parseTime
import com.kazakago.cleanarchitecture.web.response.entity.weather.ForecastResponse
import com.kazakago.cleanarchitecture.web.response.mapper.ResponseMapper

object ForecastResponseMapper : ResponseMapper<ForecastResponse, Forecast> {

    override fun map(source: ForecastResponse): Forecast {
        return Forecast(
                telop = source.telop,
                date = source.date.parseTime(),
                dateLabel = source.dateLabel,
                imageUrl = source.image.link,
                maxTemperature = source.temperature.max?.celsius,
                minTemperature = source.temperature.min?.celsius)
    }

}
