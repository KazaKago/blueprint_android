package com.kazakago.blueprint.data.api.response.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DescriptionResponse(
    @Json(name = "text")
    val text: String,
    @Json(name = "publicTime")
    val publicTime: String
)
