package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json

data class LinkResponse(
        //市区町村名
        @Json(name = "name")
        val name: String,
        //対応するlivedoor 天気情報のURL
        @Json(name = "link")
        val link: String
)