package com.kazakago.blueprint.data.resource.entity.city

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PrefEntity(
    @Json(name = "title")
    val title: String,
    @Json(name = "city")
    val cityList: List<CityEntity>
)
