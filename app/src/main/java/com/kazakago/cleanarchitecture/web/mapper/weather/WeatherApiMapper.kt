package com.kazakago.cleanarchitecture.web.mapper.weather

import com.kazakago.cleanarchitecture.domain.mapper.EntityMapper
import com.kazakago.cleanarchitecture.domain.model.weather.Description
import com.kazakago.cleanarchitecture.domain.model.weather.Forecast
import com.kazakago.cleanarchitecture.domain.model.weather.Location
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.web.entity.weather.WeatherApiEntity
import com.kazakago.cleanarchitecture.web.extension.parseTime

object WeatherApiMapper : EntityMapper<WeatherApiEntity, Weather> {

    override fun map(source: WeatherApiEntity): Weather {
        return Weather(
                location = source.location.let {
                    Location(
                            area = it.area,
                            prefecture = it.prefecture,
                            city = it.city)
                },
                title = source.title,
                link = source.link,
                publicTime = source.publicTime.parseTime(),
                description = source.description.let {
                    Description(
                            text = it.text,
                            publicTime = it.publicTime.parseTime())
                },
                forecasts = source.forecasts.map {
                    Forecast(
                            telop = it.telop,
                            date = it.date.parseTime(),
                            dateLabel = it.dateLabel,
                            imageUrl = it.image.link,
                            maxTemperature = it.temperature.max?.celsius,
                            minTemperature = it.temperature.min?.celsius)
                })
    }

}
