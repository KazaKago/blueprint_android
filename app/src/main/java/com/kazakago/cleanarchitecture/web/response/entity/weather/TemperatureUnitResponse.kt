package com.kazakago.cleanarchitecture.web.response.entity.weather

data class TemperatureUnitResponse(
        //摂氏
        val celsius: Float = 0f,
        //華氏
        val fahrenheit: Float = 0f
)