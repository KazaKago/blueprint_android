package com.kazakago.cleanarchitecture.data.api.response.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageResponse(
    @Json(name = "title")
    val title: String,
    @Json(name = "link")
    val link: String?,
    @Json(name = "url")
    val url: String,
    @Json(name = "width")
    val width: Int,
    @Json(name = "height")
    val height: Int
)
