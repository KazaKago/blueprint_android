package com.kazakago.cleanarchitecture.data.dao

import com.kazakago.cleanarchitecture.data.entity.weather.WeatherEntity
import io.realm.Realm

class WeatherDao(private val realm: Realm) {

    fun find(cityId: String): WeatherEntity? {
        return realm.where(WeatherEntity::class.java)
                .equalTo("cityId", cityId)
                .findFirst()
    }

    fun exist(cityId: String): Boolean {
        return 0 <= realm.where(WeatherEntity::class.java)
                .equalTo("cityId", cityId)
                .count()
    }

    fun insert(weather: WeatherEntity) {
        realm.copyToRealm(weather)
    }

    fun delete(cityId: String) {
        find(cityId)?.let {
            it.forecasts.forEach {
                it.temperature?.let {
                    it.max?.deleteFromRealm()
                    it.min?.deleteFromRealm()
                    it.deleteFromRealm()
                }
                it.image?.deleteFromRealm()
            }
            it.forecasts.deleteAllFromRealm()
            it.copyright?.let {
                it.provider.deleteAllFromRealm()
                it.image?.deleteFromRealm()
                it.deleteFromRealm()
            }
            it.location?.deleteFromRealm()
            it.description?.deleteFromRealm()
            it.pinpointLocations.deleteAllFromRealm()
            it.deleteFromRealm()
        }
    }

}
