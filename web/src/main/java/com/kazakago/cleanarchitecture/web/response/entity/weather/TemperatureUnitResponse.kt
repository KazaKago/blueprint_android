package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class TemperatureUnitResponse(
        //摂氏
        @Json(name = "celsius")
        val celsius: Float,
        //華氏
        @Json(name = "fahrenheit")
        val fahrenheit: Float
)