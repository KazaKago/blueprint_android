package com.kazakago.cleanarchitecture.web.response.entity.weather

data class ImageResponse(
        //天気（晴れ、曇り、雨など）
        val title: String = "",
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        val link: String = "",
        //天気アイコンのURL
        val url: String = "",
        //天気アイコンの幅
        val width: Int = 0,
        //天気アイコンの高さ
        val height: Int = 0
)
