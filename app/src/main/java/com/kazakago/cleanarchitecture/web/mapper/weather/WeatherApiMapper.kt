package com.kazakago.cleanarchitecture.web.mapper.weather

import com.kazakago.cleanarchitecture.domain.mapper.EntityMapper
import com.kazakago.cleanarchitecture.domain.model.weather.*
import com.kazakago.cleanarchitecture.web.entity.weather.WeatherApiEntity

/**
 * Created by tamura_k on 2016/12/08.
 */
object WeatherApiMapper : EntityMapper<WeatherApiEntity, WeatherModel> {

    override fun map(source: WeatherApiEntity): WeatherModel {
        val weather = WeatherModel()
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

}
