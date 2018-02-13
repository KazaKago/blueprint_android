package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class LocationResponse(
        //地方名（例・九州地方）
        @Json(name = "area")
        val area: String,
        //都道府県名（例・福岡県）
        @Json(name = "prefecture")
        val prefecture: String,
        //1次細分区名（例・八幡）
        @Json(name = "city")
        val city: String
)