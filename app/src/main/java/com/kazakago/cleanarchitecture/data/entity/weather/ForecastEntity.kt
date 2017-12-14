package com.kazakago.cleanarchitecture.data.entity.weather

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class ForecastEntity(
        @PrimaryKey(autoGenerate = true)
        val forecastId: Int,

        //予報日
        val date: String?,
        //予報日(今日、明日、明後日のいずれか)
        val dateLabel: String?,
        //天気（晴れ、曇り、雨など）
        val telop: String?,
        //画像
        @Embedded
        val image: ImageEntity?,
        //気温
        @Embedded
        val temperature: TemperatureEntity?
)