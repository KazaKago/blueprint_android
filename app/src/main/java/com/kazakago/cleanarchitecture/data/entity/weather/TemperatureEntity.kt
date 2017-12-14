package com.kazakago.cleanarchitecture.data.entity.weather

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class TemperatureEntity(
        @PrimaryKey(autoGenerate = true)
        val temperatureId: Int,

        //最高気温
        val max: TemperatureUnitEntity?,
        //最低気温
        val min: TemperatureUnitEntity?
)