package com.kazakago.cleanarchitecture.data.database.mapper.weather

import com.kazakago.cleanarchitecture.data.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.WeatherEntity
import com.kazakago.cleanarchitecture.domain.mapper.ReversibleEntityMapper
import com.kazakago.cleanarchitecture.domain.model.weather.Description
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Location
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import java.util.*

object WeatherMapper : ReversibleEntityMapper<WeatherEntity, Weather> {

    override fun map(source: WeatherEntity): Weather {
        val weather = Weather(
                location = source.location.let {
                    Location(
                            area = it.area,
                            prefecture = it.prefecture,
                            city = it.city)
                },
                title = source.title,
                link = source.link,
                publicTime = Date(source.publicTime),
                description = source.description.let {
                    Description(
                            text = it.text,
                            publicTime = Date(it.publicTime))
                },
                forecasts = source.forecasts.map {
                    Forecast(
                            telop = it.telop,
                            date = Date(it.date),
                            dateLabel = it.dateLabel,
                            imageUrl = it.imageUrl,
                            maxTemperature = it.maxTemperature,
                            minTemperature = it.minTemperature)
                })
        weather.cityId = source.cityId
        return weather
    }

    override fun reverse(destination: Weather): WeatherEntity {
        return WeatherEntity(
                cityId = destination.cityId,
                location = destination.location.let {
                    LocationEntity(
                            area = it.area,
                            prefecture = it.prefecture,
                            city = it.city)
                },
                title = destination.title,
                link = destination.link,
                publicTime = destination.publicTime.time,
                description = destination.description.let {
                    DescriptionEntity(
                            text = it.text,
                            publicTime = it.publicTime.time)
                },
                forecasts = destination.forecasts.map {
                    ForecastEntity(
                            telop = it.telop,
                            date = it.date.time,
                            dateLabel = it.dateLabel,
                            imageUrl = it.imageUrl,
                            maxTemperature = it.maxTemperature,
                            minTemperature = it.minTemperature)
                })
    }

}
