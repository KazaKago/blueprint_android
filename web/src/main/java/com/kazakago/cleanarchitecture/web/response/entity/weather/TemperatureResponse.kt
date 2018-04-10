package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class TemperatureResponse(
        @Json(name = "max")
        val max: TemperatureUnitResponse?,
        @Json(name = "min")
        val min: TemperatureUnitResponse?
)