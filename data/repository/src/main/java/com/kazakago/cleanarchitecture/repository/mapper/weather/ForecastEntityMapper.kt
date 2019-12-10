package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Forecast
import java.net.URL
import java.util.*

object ForecastEntityMapper {

    fun map(source: ForecastEntity): Forecast {
        return Forecast(
            telop = source.telop,
            date = Date(source.date),
            dateLabel = source.dateLabel,
            imageUrl = URL(source.imageUrl),
            maxTemperature = source.maxTemperature,
            minTemperature = source.minTemperature
        )
    }

    fun reverse(cityId: CityId, destination: Forecast): ForecastEntity {
        return ForecastEntity(
            cityId = cityId.value,
            telop = destination.telop,
            date = destination.date.time,
            dateLabel = destination.dateLabel,
            imageUrl = destination.imageUrl.toString(),
            maxTemperature = destination.maxTemperature,
            minTemperature = destination.minTemperature
        )
    }

}
