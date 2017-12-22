package com.kazakago.cleanarchitecture.data.database.mapper.weather

import com.kazakago.cleanarchitecture.data.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import java.util.*

object ForecastEntityMapper {

    fun map(source: ForecastEntity): Forecast {
        return Forecast(
                telop = source.telop,
                date = Date(source.date),
                dateLabel = source.dateLabel,
                imageUrl = source.imageUrl,
                maxTemperature = source.maxTemperature,
                minTemperature = source.minTemperature)
    }

    fun reverse(cityId: String, destination: Forecast): ForecastEntity {
        return ForecastEntity(
                cityId = cityId,
                telop = destination.telop,
                date = destination.date.time,
                dateLabel = destination.dateLabel,
                imageUrl = destination.imageUrl,
                maxTemperature = destination.maxTemperature,
                minTemperature = destination.minTemperature)
    }

}
