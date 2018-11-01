package com.kazakago.cleanarchitecture.data.file.entity.city

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityEntity(
    @Json(name = "id")
    val id: String,
    @Json(name = "title")
    val title: String
)
