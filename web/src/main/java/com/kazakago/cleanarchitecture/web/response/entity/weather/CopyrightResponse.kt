package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class CopyrightResponse(
        @Json(name = "title")
        val title: String,
        @Json(name = "link")
        val link: String,
        @Json(name = "image")
        val image: ImageResponse,
        @Json(name = "provider")
        val provider: List<LinkResponse>
)