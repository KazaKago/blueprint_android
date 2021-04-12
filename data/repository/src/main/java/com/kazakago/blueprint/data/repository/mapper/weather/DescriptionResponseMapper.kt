package com.kazakago.blueprint.data.repository.mapper.weather

import com.kazakago.blueprint.data.api.response.weather.DescriptionResponse
import com.kazakago.blueprint.data.repository.global.extension.parseDateTime
import com.kazakago.blueprint.domain.model.hierarchy.weather.Description

internal class DescriptionResponseMapper {

    fun map(source: DescriptionResponse): Description {
        return Description(
            text = source.text,
            publicTime = source.publicTime.parseDateTime()
        )
    }
}
