package com.kazakago.cleanarchitecture.data.repository.mapper.weather

import com.kazakago.cleanarchitecture.data.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Forecast
import java.net.URL
import java.time.LocalDate

internal class ForecastEntityMapper {

    fun map(source: ForecastEntity): Forecast {
        return Forecast(
            telop = source.telop,
            date = LocalDate.parse(source.date),
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
            date = destination.date.toString(),
            dateLabel = destination.dateLabel,
            imageUrl = destination.imageUrl.toString(),
            maxTemperature = destination.maxTemperature,
            minTemperature = destination.minTemperature
        )
    }
}
