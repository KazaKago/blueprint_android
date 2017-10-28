package com.kazakago.cleanarchitecture.web.mapper.weather

import com.kazakago.cleanarchitecture.domain.mapper.EntityMapper
import com.kazakago.cleanarchitecture.domain.model.weather.*
import com.kazakago.cleanarchitecture.web.entity.weather.WeatherApiEntity

object WeatherApiMapper : EntityMapper<WeatherApiEntity, WeatherModel> {

    override fun map(source: WeatherApiEntity): WeatherModel {
        return WeatherModel(
                location = source.location.let {
                    LocationModel(
                            area = it.area,
                            prefecture = it.prefecture,
                            city = it.city)
                },
                title = source.title,
                link = source.link,
                publicTime = source.publicTime,
                description = source.description.let {
                    DescriptionModel(
                            text = it.text,
                            publicTime = it.publicTime)
                },
                forecasts = source.forecasts.map {
                    ForecastModel(
                            telop = it.telop,
                            date = it.date,
                            dateLabel = it.dateLabel,
                            image = it.image.let {
                                ImageModel(
                                        title = it.title,
                                        height = it.height,
                                        link = it.link,
                                        url = it.url,
                                        width = it.width)
                            },
                            temperature = it.temperature.let {
                                TemperatureModel(
                                        max = it.max.let {
                                            TemperatureUnitModel(
                                                    celsius = it.celsius,
                                                    fahrenheit = it.fahrenheit)
                                        },
                                        min = it.min.let {
                                            TemperatureUnitModel(
                                                    celsius = it.celsius,
                                                    fahrenheit = it.fahrenheit)
                                        })
                            })
                },
                pinpointLocations = source.pinpointLocations.map {
                    LinkModel(
                            name = it.name,
                            link = it.link)
                },
                copyright = source.copyright.let {
                    CopyrightModel(
                            title = it.title,
                            link = it.link,
                            image = it.image.let {
                                ImageModel(
                                        title = it.title,
                                        height = it.height,
                                        link = it.link,
                                        url = it.url,
                                        width = it.width)
                            },
                            provider = it.provider.map {
                                LinkModel(
                                        name = it.name,
                                        link = it.link)
                            }
                    )
                })
    }

}
