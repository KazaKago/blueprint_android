package com.kazakago.cleanarchitecture.data.repository.mapper.weather

import com.kazakago.cleanarchitecture.data.api.entity.weather.LocationResponse
import com.kazakago.cleanarchitecture.domain.model.weather.Location

internal class LocationResponseMapper {

    fun map(source: LocationResponse): Location {
        return Location(
            area = source.area,
            prefecture = source.prefecture,
            city = source.city
        )
    }

}
