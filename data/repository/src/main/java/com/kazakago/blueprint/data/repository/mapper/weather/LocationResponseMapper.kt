package com.kazakago.blueprint.data.repository.mapper.weather

import com.kazakago.blueprint.data.api.response.weather.LocationResponse
import com.kazakago.blueprint.domain.model.hierarchy.weather.Location

internal class LocationResponseMapper {

    fun map(source: LocationResponse): Location {
        return Location(
            area = source.area,
            prefecture = source.prefecture,
            city = source.city
        )
    }
}
