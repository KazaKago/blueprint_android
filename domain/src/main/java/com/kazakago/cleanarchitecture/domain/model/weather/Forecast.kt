package com.kazakago.cleanarchitecture.domain.model.weather

import java.util.*

data class Forecast(
        //予報日
        var date: Date,
        //予報日(今日、明日、明後日のいずれか)
        var dateLabel: String,
        //天気（晴れ、曇り、雨など）
        var telop: String,
        //画像URL
        var imageUrl: String,
        //最高気温
        var maxTemperature: Float?,
        //最低気温
        var minTemperature: Float?
)