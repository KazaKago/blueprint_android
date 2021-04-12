package com.kazakago.blueprint.data.repository.mapper.city

import com.kazakago.blueprint.data.resource.entity.city.PrefEntity
import com.kazakago.blueprint.domain.model.hierarchy.city.City
import com.kazakago.blueprint.domain.model.hierarchy.city.CityId

internal class CityEntityMapper {

    fun map(source: PrefEntity): List<City> {
        val prefTitle = source.title
        return source.cityList.map {
            City(
                id = CityId(it.id),
                name = prefTitle + " " + it.title
            )
        }
    }
}
