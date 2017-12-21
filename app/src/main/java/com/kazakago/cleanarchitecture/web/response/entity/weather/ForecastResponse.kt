package com.kazakago.cleanarchitecture.web.response.entity.weather

data class ForecastResponse(
        //予報日
        val date: String,
        //予報日(今日、明日、明後日のいずれか)
        val dateLabel: String,
        //天気（晴れ、曇り、雨など）
        val telop: String,
        //画像
        val image: ImageResponse,
        //気温
        val temperature: TemperatureResponse
)