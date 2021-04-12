package com.kazakago.blueprint.data.repository.mapper.weather

import com.kazakago.blueprint.data.database.entity.weather.DescriptionEntity
import com.kazakago.blueprint.domain.model.hierarchy.city.CityId
import com.kazakago.blueprint.domain.model.hierarchy.weather.Description
import java.time.LocalDateTime

internal class DescriptionEntityMapper {

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
