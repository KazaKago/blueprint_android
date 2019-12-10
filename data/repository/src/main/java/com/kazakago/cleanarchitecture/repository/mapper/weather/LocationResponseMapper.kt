package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.api.entity.weather.LocationResponse
import com.kazakago.cleanarchitecture.model.weather.Location
import com.kazakago.cleanarchitecture.repository.mapper.ResponseMapper

object LocationResponseMapper : ResponseMapper<LocationResponse, Location> {

    override fun map(source: LocationResponse): Location {
        return Location(
            area = source.area,
            prefecture = source.prefecture,
            city = source.city
        )
    }

}
