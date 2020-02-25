package com.kazakago.cleanarchitecture.data.api.response.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
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