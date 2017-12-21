package com.kazakago.cleanarchitecture.web.response.entity.weather

import com.squareup.moshi.Json

data class TemperatureResponse(
        //最高気温
        @Json(name = "max")
        val max: TemperatureUnitResponse?,
        //最低気温
        @Json(name = "min")
        val min: TemperatureUnitResponse?
)