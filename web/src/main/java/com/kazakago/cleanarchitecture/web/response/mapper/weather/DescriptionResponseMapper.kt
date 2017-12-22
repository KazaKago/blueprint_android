package com.kazakago.cleanarchitecture.web.response.mapper.weather

import com.kazakago.cleanarchitecture.domain.model.weather.Description
import com.kazakago.cleanarchitecture.web.extension.parseDateTime
import com.kazakago.cleanarchitecture.web.response.entity.weather.DescriptionResponse
import com.kazakago.cleanarchitecture.web.response.mapper.ResponseMapper

object DescriptionResponseMapper : ResponseMapper<DescriptionResponse, Description> {

    override fun map(source: DescriptionResponse): Description {
        return Description(
                text = source.text,
                publicTime = source.publicTime.parseDateTime())
    }

}
