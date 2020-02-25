package com.kazakago.cleanarchitecture.data.repository.mapper.weather

import com.kazakago.cleanarchitecture.data.api.response.weather.ForecastResponse
import com.kazakago.cleanarchitecture.data.repository.global.extension.parseDate
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Forecast
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
