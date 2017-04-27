package com.kazakago.cleanarchitecture.web.entity.weather

/**
 * Temperature API Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
data class TemperatureApiEntity(
        //最高気温
        var max: TemperatureUnitApiEntity? = null,
        //最低気温
        var min: TemperatureUnitApiEntity? = null
)