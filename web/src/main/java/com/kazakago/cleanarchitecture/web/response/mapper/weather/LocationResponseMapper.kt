package com.kazakago.cleanarchitecture.web.response.mapper.weather

import com.kazakago.cleanarchitecture.domain.model.weather.Location
import com.kazakago.cleanarchitecture.web.response.entity.weather.LocationResponse
import com.kazakago.cleanarchitecture.web.response.mapper.ResponseMapper

object LocationResponseMapper : ResponseMapper<LocationResponse, Location> {

    override fun map(source: LocationResponse): Location {
        return Location(
                area = source.area,
                prefecture = source.prefecture,
                city = source.city)
    }

}
