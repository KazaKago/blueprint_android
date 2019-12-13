package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Description
import java.time.LocalDateTime

internal object DescriptionEntityMapper {

    fun map(source: DescriptionEntity): Description {
        return Description(
            text = source.text,
            publicTime = LocalDateTime.parse(source.publicTime)
        )
    }

    fun reverse(cityId: CityId, destination: Description): DescriptionEntity {
        return DescriptionEntity(
            cityId = cityId.value,
            text = destination.text,
            publicTime = destination.publicTime.toString()
        )
    }

}
