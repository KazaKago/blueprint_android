package com.kazakago.cleanarchitecture.api.entity.weather

import com.kazakago.cleanarchitecture.api.entity.weather.*
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WeatherResponse(
    @Json(name = "location")
    val location: LocationResponse,
    @Json(name = "title")
    val title: String,
    @Json(name = "link")
    val link: String,
    @Json(name = "publicTime")
    val publicTime: String,
    @Json(name = "description")
    val description: DescriptionResponse,
    @Json(name = "forecasts")
    val forecasts: List<ForecastResponse>,
    @Json(name = "pinpointLocations")
    val pinpointLocations: List<LinkResponse>,
    @Json(name = "copyright")
    val copyright: CopyrightResponse
)