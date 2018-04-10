package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class DescriptionResponse(
        @Json(name = "text")
        val text: String,
        @Json(name = "publicTime")
        val publicTime: String
)