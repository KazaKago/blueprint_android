package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class LinkResponse(
        @Json(name = "name")
        val name: String,
        @Json(name = "link")
        val link: String
)