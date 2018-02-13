package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class DescriptionResponse(
        //天気概況文
        @Json(name = "text")
        val text: String,
        //天気概況文の発表時刻
        @Json(name = "publicTime")
        val publicTime: String
)