package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

open class TemperatureUnitEntity(
        //摂氏
        var celsius: Float = 0f,
        //華氏
        var fahrenheit: Float = 0f
) : RealmObject()
