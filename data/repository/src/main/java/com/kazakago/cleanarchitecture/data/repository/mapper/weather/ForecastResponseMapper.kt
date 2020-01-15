package com.kazakago.cleanarchitecture.data.repository.mapper.weather

import com.kazakago.cleanarchitecture.data.api.entity.weather.ForecastResponse
import com.kazakago.cleanarchitecture.data.repository.extension.parseDate
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
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
