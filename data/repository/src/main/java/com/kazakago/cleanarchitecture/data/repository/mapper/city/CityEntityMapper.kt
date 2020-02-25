package com.kazakago.cleanarchitecture.data.repository.mapper.city

import com.kazakago.cleanarchitecture.data.resource.entity.city.PrefEntity
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId

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
