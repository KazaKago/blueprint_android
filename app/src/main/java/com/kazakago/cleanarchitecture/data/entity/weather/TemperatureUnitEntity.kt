package com.kazakago.cleanarchitecture.data.entity.weather

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class TemperatureUnitEntity(
        @PrimaryKey(autoGenerate = true)
        val temperatureUnitId: Int,

        //摂氏
        val celsius: Float?,
        //華氏
        val fahrenheit: Float?
)