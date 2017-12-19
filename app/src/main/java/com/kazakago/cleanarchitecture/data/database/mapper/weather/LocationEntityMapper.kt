package com.kazakago.cleanarchitecture.data.database.mapper.weather

import com.kazakago.cleanarchitecture.data.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.domain.model.weather.Location

object LocationEntityMapper {

    fun map(source: LocationEntity?): Location {
        return Location(
                area = source?.area ?: "",
                prefecture = source?.prefecture ?: "",
                city = source?.city ?: "")
    }

    fun reverse(cityId: String, destination: Location): LocationEntity {
        return LocationEntity(
                cityId = cityId,
                area = destination.area,
                prefecture = destination.prefecture,
                city = destination.city)
    }

}
