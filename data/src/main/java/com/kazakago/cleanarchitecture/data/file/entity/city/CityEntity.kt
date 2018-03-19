package com.kazakago.cleanarchitecture.data.file.entity.city

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class CityEntity(
        @Json(name = "id")
        val id: String,
        @Json(name = "title")
        val title: String
)
