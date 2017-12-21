package com.kazakago.cleanarchitecture.web.response.entity.weather

data class TemperatureResponse(
        //最高気温
        val max: TemperatureUnitResponse?,
        //最低気温
        val min: TemperatureUnitResponse?
)