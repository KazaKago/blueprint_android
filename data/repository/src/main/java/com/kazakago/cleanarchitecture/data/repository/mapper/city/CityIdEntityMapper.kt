package com.kazakago.cleanarchitecture.data.repository.mapper.city

import com.kazakago.cleanarchitecture.data.memory.entity.weather.CityIdEntity
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId

internal class CityIdEntityMapper {

    fun reverse(source: CityId): CityIdEntity {
        return CityIdEntity(source.value)
    }

}
