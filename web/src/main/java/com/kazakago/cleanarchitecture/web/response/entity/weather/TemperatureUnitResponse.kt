package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class TemperatureUnitResponse(
        @Json(name = "celsius")
        val celsius: Float,
        @Json(name = "fahrenheit")
        val fahrenheit: Float
)