package com.kazakago.cleanarchitecture.repository.mapper.city

import com.kazakago.cleanarchitecture.memory.entity.weather.CityIdEntity
import com.kazakago.cleanarchitecture.model.city.CityId

internal class CityIdEntityMapper {

    fun reverse(source: CityId): CityIdEntity {
        return CityIdEntity(source.value)
    }

}
