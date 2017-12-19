package com.kazakago.cleanarchitecture.data.database.mapper.weather

import com.kazakago.cleanarchitecture.data.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.domain.model.weather.Description
import java.util.*

object DescriptionEntityMapper {

    fun map(source: DescriptionEntity?): Description {
        return Description(
                text = source?.text ?: "",
                publicTime = Date(source?.publicTime ?: 0))
    }

    fun reverse(cityId: String, destination: Description): DescriptionEntity {
        return DescriptionEntity(
                cityId = cityId,
                text = destination.text,
                publicTime = destination.publicTime.time)
    }

}
