package com.kazakago.cleanarchitecture.api.entity.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TemperatureResponse(
    @Json(name = "max")
    val max: TemperatureUnitResponse?,
    @Json(name = "min")
    val min: TemperatureUnitResponse?
)