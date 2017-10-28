package com.kazakago.cleanarchitecture.web.entity.weather

data class TemperatureApiEntity(
        //最高気温
        val max: TemperatureUnitApiEntity,
        //最低気温
        val min: TemperatureUnitApiEntity
)