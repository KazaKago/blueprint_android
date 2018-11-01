package com.kazakago.cleanarchitecture.data.database.mapper.weather

import com.kazakago.cleanarchitecture.data.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Description
import java.util.*

object DescriptionEntityMapper {

    fun map(source: DescriptionEntity): Description {
        return Description(
            text = source.text,
            publicTime = Date(source.publicTime)
        )
    }

    fun reverse(cityId: CityId, destination: Description): DescriptionEntity {
        return DescriptionEntity(
            cityId = cityId.value,
            text = destination.text,
            publicTime = destination.publicTime.time
        )
    }

}
