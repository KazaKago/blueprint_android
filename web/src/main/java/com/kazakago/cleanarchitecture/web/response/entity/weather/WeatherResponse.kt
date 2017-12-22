package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json

data class WeatherResponse(
        //予報を発表した地域を定義
        @Json(name = "location")
        val location: LocationResponse,
        //タイトル・見出し
        @Json(name = "title")
        val title: String,
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        @Json(name = "link")
        val link: String,
        //予報の発表日時
        @Json(name = "publicTime")
        val publicTime: String,
        //天気概況文
        @Json(name = "description")
        val description: DescriptionResponse,
        //府県天気予報の予報日毎の配列
        @Json(name = "forecasts")
        val forecasts: List<ForecastResponse>,
        //ピンポイント予報の発表地点の配列
        @Json(name = "pinpointLocations")
        val pinpointLocations: List<LinkResponse>,
        //コピーライト
        @Json(name = "copyright")
        val copyright: CopyrightResponse
)