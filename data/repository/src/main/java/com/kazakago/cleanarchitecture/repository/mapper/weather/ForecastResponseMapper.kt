package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.api.entity.weather.ForecastResponse
import com.kazakago.cleanarchitecture.model.weather.Forecast
import com.kazakago.cleanarchitecture.repository.extension.parseDate
import java.net.URL

internal class ForecastResponseMapper {

    fun map(response: ForecastResponse): Forecast {
        return Forecast(
            telop = response.telop,
            date = response.date.parseDate(),
            dateLabel = response.dateLabel,
            imageUrl = URL(response.image.url),
            maxTemperature = response.temperature.max?.celsius,
            minTemperature = response.temperature.min?.celsius
        )
    }

}
