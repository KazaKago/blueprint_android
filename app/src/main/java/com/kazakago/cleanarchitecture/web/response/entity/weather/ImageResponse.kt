package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json

data class ImageResponse(
        //天気（晴れ、曇り、雨など）
        @Json(name = "title")
        val title: String,
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        @Json(name = "link")
        val link: String?,
        //天気アイコンのURL
        @Json(name = "url")
        val url: String,
        //天気アイコンの幅
        @Json(name = "width")
        val width: Int,
        //天気アイコンの高さ
        @Json(name = "height")
        val height: Int
)
