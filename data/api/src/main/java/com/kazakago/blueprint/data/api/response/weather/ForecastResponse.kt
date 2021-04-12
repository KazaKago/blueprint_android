package com.kazakago.blueprint.data.api.response.weather

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    @Json(name = "date")
    val date: String,
    @Json(name = "dateLabel")
    val dateLabel: String,
    @Json(name = "telop")
    val telop: String,
    @Json(name = "image")
    val image: ImageResponse,
    @Json(name = "temperature")
    val temperature: TemperatureResponse
)
