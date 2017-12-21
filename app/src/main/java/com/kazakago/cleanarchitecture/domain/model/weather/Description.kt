package com.kazakago.cleanarchitecture.domain.model.weather

import java.util.*

data class Description(
        //天気概況文
        val text: String,
        //天気概況文の発表時刻
        val publicTime: Date
)