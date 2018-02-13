package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class CopyrightResponse(
        //コピーライトの文言
        @Json(name = "title")
        val title: String,
        //livedoor 天気情報のURL
        @Json(name = "link")
        val link: String,
        //livedoor 天気情報へのURL、アイコンなど
        @Json(name = "image")
        val image: ImageResponse,
        //livedoor 天気情報で使用している気象データの配信元
        @Json(name = "provider")
        val provider: List<LinkResponse>
)