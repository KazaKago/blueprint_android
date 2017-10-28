package com.kazakago.cleanarchitecture.web.entity.weather

data class ForecastApiEntity(
        //予報日
        val date: String = "",
        //予報日(今日、明日、明後日のいずれか)
        val dateLabel: String = "",
        //天気（晴れ、曇り、雨など）
        val telop: String = "",
        //画像
        val image: ImageApiEntity = ImageApiEntity(),
        //気温
        val temperature: TemperatureApiEntity = TemperatureApiEntity()
)