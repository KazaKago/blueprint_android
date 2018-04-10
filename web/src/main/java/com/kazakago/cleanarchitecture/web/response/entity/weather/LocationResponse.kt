package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class LocationResponse(
        @Json(name = "area")
        val area: String,
        @Json(name = "prefecture")
        val prefecture: String,
        @Json(name = "city")
        val city: String
)