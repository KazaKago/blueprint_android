package com.kazakago.cleanarchitecture.data.mapper.weather

import com.kazakago.cleanarchitecture.data.entity.weather.*
import com.kazakago.cleanarchitecture.domain.mapper.ReversibleEntityMapper
import com.kazakago.cleanarchitecture.domain.model.weather.*
import io.realm.RealmList

/**
 * Created by tamura_k on 2016/12/08.
 */
object WeatherMapper : ReversibleEntityMapper<WeatherEntity, WeatherModel> {

    override fun map(source: WeatherEntity): WeatherModel {
        return WeatherModel(
                cityId = source.cityId ?: "",
                location = source.location?.let { LocationModel(area = it.area ?: "", prefecture = it.prefecture ?: "", city = it.city ?: "") },
                title = source.title ?: "",
                link = source.link ?: "",
                publicTime = source.publicTime,
                description = source.description.let { DescriptionModel(text = it.text, publicTime = it.publicTime) },
                forecasts = source.forecasts.map {
                    ForecastModel(
                            telop = it.telop,
                            date = it.date,
                            dateLabel = it.dateLabel,
                            image = it.image.let { ImageModel(title = it.title, height = it.height, link = it.link, url = it.url, width = it.width) },
                            temperature = it.temperature.let {
                                TemperatureModel(
                                        max = it.max.let { TemperatureUnitModel(celsius = it.celsius, fahrenheit = it.fahrenheit) },
                                        min = it.min.let { TemperatureUnitModel(celsius = it.celsius, fahrenheit = it.fahrenheit) })
                            })
                },
                pinpointLocations = source.pinpointLocations.map { LinkModel(name = it.name, link = it.link) },
                copyright = source.copyright.let {
                    CopyrightModel(
                            title = it.title,
                            link = it.link,
                            image = it.image.let { ImageModel(title = it.title, height = it.height, link = it.link, url = it.url, width = it.width) },
                            provider = it.provider.map { LinkModel(name = it.name, link = it.link) }
                    )
                })
        val weather = WeatherModel(
                cityId = source.cityId
        )
        weather.cityId = source.cityId
        weather.location = source.location?.let {
            val location = LocationModel()
            location.area = it.area
            location.prefecture = it.prefecture
            location.city = it.city
            location
        }
        weather.title = source.title
        weather.link = source.link
        weather.publicTime = source.publicTime
        weather.description = source.description?.let {
            val description = DescriptionModel()
            description.text = it.text
            description.publicTime = it.publicTime
            description
        }
        weather.forecasts = source.forecasts.map {
            val forecast = ForecastModel()
            forecast.telop = it.telop
            forecast.date = it.date
            forecast.dateLabel = it.dateLabel
            forecast.image = it.image?.let {
                val image = ImageModel()
                image.title = it.title
                image.height = it.height
                image.link = it.link
                image.url = it.url
                image.width = it.width
                image
            }
            forecast.temperature = it.temperature?.let {
                val temperature = TemperatureModel()
                temperature.max = it.max?.let {
                    val temperatureUnit = TemperatureUnitModel()
                    temperatureUnit.celsius = it.celsius
                    temperatureUnit.fahrenheit = it.fahrenheit
                    temperatureUnit
                }
                temperature.min = it.min?.let {
                    val temperatureUnit = TemperatureUnitModel()
                    temperatureUnit.celsius = it.celsius
                    temperatureUnit.fahrenheit = it.fahrenheit
                    temperatureUnit
                }
                temperature
            }
            forecast
        }
        weather.pinpointLocations = source.pinpointLocations.map {
            val pinpointLocation = LinkModel()
            pinpointLocation.name = it.name
            pinpointLocation.link = it.link
            pinpointLocation
        }
        weather.copyright = source.copyright?.let {
            val copyright = CopyrightModel()
            copyright.title = it.title
            copyright.link = it.link
            copyright.image = it.image?.let {
                val image = ImageModel()
                image.title = it.title
                image.height = it.height
                image.link = it.link
                image.url = it.url
                image.width = it.width
                image
            }
            copyright.provider = it.provider.map {
                val provider = LinkModel()
                provider.name = it.name
                provider.link = it.link
                provider
            }
            copyright
        }
        return weather
    }

    override fun reverse(destination: WeatherModel): WeatherEntity {
        val weather = WeatherEntity()
        weather.cityId = destination.cityId
        weather.location = destination.location?.let {
            val location = LocationEntity()
            location.area = it.area
            location.prefecture = it.prefecture
            location.city = it.city
            location
        }
        weather.title = destination.title
        weather.link = destination.link
        weather.publicTime = destination.publicTime
        weather.description = destination.description?.let {
            val description = DescriptionEntity()
            description.text = it.text
            description.publicTime = it.publicTime
            description
        }
        val forecastRealmList = RealmList<ForecastEntity>()
        destination.forecasts.forEach {
            val forecast = ForecastEntity()
            forecast.telop = it.telop
            forecast.date = it.date
            forecast.dateLabel = it.dateLabel
            forecast.image = it.image?.let {
                val image = ImageEntity()
                image.title = it.title
                image.height = it.height
                image.link = it.link
                image.url = it.url
                image.width = it.width
                image
            }
            forecast.temperature = it.temperature?.let {
                val temperature = TemperatureEntity()
                temperature.max = it.max?.let {
                    val temperatureUnit = TemperatureUnitEntity()
                    temperatureUnit.celsius = it.celsius
                    temperatureUnit.fahrenheit = it.fahrenheit
                    temperatureUnit
                }
                temperature.min = it.min?.let {
                    val temperatureUnit = TemperatureUnitEntity()
                    temperatureUnit.celsius = it.celsius
                    temperatureUnit.fahrenheit = it.fahrenheit
                    temperatureUnit
                }
                temperature
            }
            forecastRealmList.add(forecast)
        }
        weather.forecasts = forecastRealmList
        val pinpointLocationsRealmList = RealmList<LinkEntity>()
        destination.pinpointLocations.forEach {
            val pinpointLocation = LinkEntity()
            pinpointLocation.name = it.name
            pinpointLocation.link = it.link
            pinpointLocationsRealmList.add(pinpointLocation)
        }
        weather.pinpointLocations = pinpointLocationsRealmList
        weather.copyright = destination.copyright?.let {
            val copyright = CopyrightEntity()
            copyright.title = it.title
            copyright.link = it.link
            copyright.image = it.image?.let {
                val image = ImageEntity()
                image.title = it.title
                image.height = it.height
                image.link = it.link
                image.url = it.url
                image.width = it.width
                image
            }
            val providerRealmList = RealmList<LinkEntity>()
            it.provider.forEach {
                val provider = LinkEntity()
                provider.name = it.name
                provider.link = it.link
                providerRealmList.add(provider)
            }
            copyright.provider = providerRealmList
            copyright
        }
        return weather
    }

}
