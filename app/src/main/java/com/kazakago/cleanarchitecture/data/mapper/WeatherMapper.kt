package com.kazakago.cleanarchitecture.data.mapper

import com.kazakago.cleanarchitecture.data.entity.weather.*
import com.kazakago.cleanarchitecture.domain.model.weather.*
import io.realm.RealmList

/**
 * Created by tamura_k on 2016/12/08.
 */
object WeatherMapper {

    fun execute(entity: WeatherEntity): WeatherModel {
        val weather = WeatherModel()
        weather.cityId = entity.cityId
        weather.location = entity.location?.let {
            val location = LocationModel()
            location.area = it.area
            location.prefecture = it.prefecture
            location.city = it.city
            location
        }
        weather.title = entity.title
        weather.link = entity.link
        weather.publicTime = entity.publicTime
        weather.description = entity.description?.let {
            val description = DescriptionModel()
            description.text = it.text
            description.publicTime = it.publicTime
            description
        }
        weather.forecasts = entity.forecasts.map {
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
            forecast
        }
        weather.pinpointLocations = entity.pinpointLocations.map {
            val pinpointLocation = LinkModel()
            pinpointLocation.name = it.name
            pinpointLocation.link = it.link
            pinpointLocation
        }
        weather.copyright = entity.copyright?.let {
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

    fun execute(model: WeatherModel): WeatherEntity {
        val weather = WeatherEntity()
        weather.cityId = model.cityId
        weather.location = model.location?.let {
            val location = LocationEntity()
            location.area = it.area
            location.prefecture = it.prefecture
            location.city = it.city
            location
        }
        weather.title = model.title
        weather.link = model.link
        weather.publicTime = model.publicTime
        weather.description = model.description?.let {
            val description = DescriptionEntity()
            description.text = it.text
            description.publicTime = it.publicTime
            description
        }
        val forecastRealmList = RealmList<ForecastEntity>()
        model.forecasts.forEach {
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
            forecastRealmList.add(forecast)
        }
        weather.forecasts = forecastRealmList
        val pinpointLocationsRealmList = RealmList<LinkEntity>()
        model.pinpointLocations.forEach {
            val pinpointLocation = LinkEntity()
            pinpointLocation.name = it.name
            pinpointLocation.link = it.link
            pinpointLocationsRealmList.add(pinpointLocation)
        }
        weather.pinpointLocations = pinpointLocationsRealmList
        weather.copyright = model.copyright?.let {
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
