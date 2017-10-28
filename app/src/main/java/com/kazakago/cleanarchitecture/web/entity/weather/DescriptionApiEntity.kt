package com.kazakago.cleanarchitecture.web.entity.weather

data class DescriptionApiEntity(
        //天気概況文
        val text: String,
        //天気概況文の発表時刻
        val publicTime: String
)