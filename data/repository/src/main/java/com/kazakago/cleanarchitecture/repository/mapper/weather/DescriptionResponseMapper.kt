package com.kazakago.cleanarchitecture.repository.mapper.weather

import com.kazakago.cleanarchitecture.api.entity.weather.DescriptionResponse
import com.kazakago.cleanarchitecture.model.weather.Description
import com.kazakago.cleanarchitecture.repository.extension.parseDateTime
import com.kazakago.cleanarchitecture.repository.mapper.ResponseMapper

object DescriptionResponseMapper : ResponseMapper<DescriptionResponse, Description> {

    override fun map(source: DescriptionResponse): Description {
        return Description(
            text = source.text,
            publicTime = source.publicTime.parseDateTime()
        )
    }

}
