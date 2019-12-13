package com.kazakago.cleanarchitecture.repository.mapper.city

import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.resource.entity.city.PrefEntity

internal class CityMapper {

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
