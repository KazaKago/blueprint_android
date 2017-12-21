package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json

data class ForecastResponse(
        //予報日
        @Json(name = "date")
        val date: String,
        //予報日(今日、明日、明後日のいずれか)
        @Json(name = "dateLabel")
        val dateLabel: String,
        //天気（晴れ、曇り、雨など）
        @Json(name = "telop")
        val telop: String,
        //画像
        @Json(name = "image")
        val image: ImageResponse,
        //気温
        @Json(name = "temperature")
        val temperature: TemperatureResponse
)