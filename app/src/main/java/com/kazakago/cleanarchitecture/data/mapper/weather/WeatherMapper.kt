package com.kazakago.cleanarchitecture.data.mapper.weather

import com.kazakago.cleanarchitecture.data.entity.weather.*
import com.kazakago.cleanarchitecture.domain.mapper.ReversibleEntityMapper
import com.kazakago.cleanarchitecture.domain.model.weather.*
import io.realm.RealmList

object WeatherMapper : ReversibleEntityMapper<WeatherEntity, WeatherModel> {

    override fun map(source: WeatherEntity): WeatherModel {
        val weather = WeatherModel(
                location = source.location.let {
                    LocationModel(
                            area = it?.area ?: "",
                            prefecture = it?.prefecture ?: "",
                            city = it?.city ?: "")
                },
                title = source.title,
                link = source.link,
                publicTime = source.publicTime,
                description = source.description.let {
                    DescriptionModel(
                            text = it?.text ?: "",
                            publicTime = it?.publicTime ?: "")
                },
                forecasts = source.forecasts.map {
                    ForecastModel(
                            telop = it.telop,
                            date = it.date,
                            dateLabel = it.dateLabel,
                            image = it.image.let {
                                ImageModel(
                                        title = it?.title ?: "",
                                        height = it?.height ?: 0,
                                        link = it?.link ?: "",
                                        url = it?.url ?: "",
                                        width = it?.width ?: 0)
                            },
                            temperature = it.temperature.let {
                                TemperatureModel(
                                        max = it?.max?.let {
                                            TemperatureUnitModel(
                                                    celsius = it.celsius,
                                                    fahrenheit = it.fahrenheit)
                                        },
                                        min = it?.min?.let {
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
                            title = it?.title ?: "",
                            link = it?.link ?: "",
                            image = it?.image.let {
                                ImageModel(
                                        title = it?.title ?: "",
                                        height = it?.height ?: 0,
                                        link = it?.link ?: "",
                                        url = it?.url ?: "",
                                        width = it?.width ?: 0)
                            },
                            provider = it?.provider?.map {
                                LinkModel(
                                        name = it.name,
                                        link = it.link)
                            } ?: listOf()
                    )
                })
        weather.cityId = source.cityId
        return weather
    }

    override fun reverse(destination: WeatherModel): WeatherEntity {
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
                publicTime = destination.publicTime,
                description = destination.description.let {
                    DescriptionEntity(
                            text = it.text,
                            publicTime = it.publicTime)
                },
                forecasts = destination.forecasts.map {
                    ForecastEntity(
                            telop = it.telop,
                            date = it.date,
                            dateLabel = it.dateLabel,
                            image = it.image.let {
                                ImageEntity(
                                        title = it.title,
                                        height = it.height,
                                        link = it.link,
                                        url = it.url,
                                        width = it.width)
                            },
                            temperature = it.temperature.let {
                                TemperatureEntity(
                                        max = it.max?.let {
                                            TemperatureUnitEntity(
                                                    celsius = it.celsius,
                                                    fahrenheit = it.fahrenheit)
                                        },
                                        min = it.min?.let {
                                            TemperatureUnitEntity(
                                                    celsius = it.celsius,
                                                    fahrenheit = it.fahrenheit)
                                        }
                                )
                            })
                }.let { RealmList<ForecastEntity>().apply { addAll(it) } },
                pinpointLocations = destination.pinpointLocations.map {
                    LinkEntity(
                            name = it.name,
                            link = it.link)
                }.let { RealmList<LinkEntity>().apply { addAll(it) } },
                copyright = destination.copyright.let {
                    CopyrightEntity(
                            title = it.title,
                            link = it.link,
                            image = it.image.let {
                                ImageEntity(
                                        title = it.title,
                                        height = it.height,
                                        link = it.link,
                                        url = it.url,
                                        width = it.width)
                            },
                            provider = it.provider.map {
                                LinkEntity(
                                        name = it.name,
                                        link = it.link)
                            }.let { RealmList<LinkEntity>().apply { addAll(it) } })
                })
    }

}
