package com.kazakago.cleanarchitecture.data.repository.mapper.weather

import com.kazakago.cleanarchitecture.data.api.response.weather.LocationResponse
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Location

internal class LocationResponseMapper {

    fun map(source: LocationResponse): Location {
        return Location(
            area = source.area,
            prefecture = source.prefecture,
            city = source.city
        )
    }

}
