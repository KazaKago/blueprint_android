package com.kazakago.cleanarchitecture.data.entity.city

import com.squareup.moshi.Json

data class CityEntity(
        @Json(name = "id")
        val id: String,
        @Json(name = "title")
        val title: String
)
