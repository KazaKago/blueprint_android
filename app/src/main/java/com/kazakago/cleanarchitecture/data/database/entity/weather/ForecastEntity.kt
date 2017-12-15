package com.kazakago.cleanarchitecture.data.database.entity.weather

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "forecast",
        foreignKeys = [(ForeignKey(entity = WeatherEntity::class,
                parentColumns = ["city_id"],
                childColumns = ["id"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE))])
data class ForecastEntity(
        //予報日
        @ColumnInfo(name = "date")
        val date: Long,
        //予報日(今日、明日、明後日のいずれか)
        @ColumnInfo(name = "dateLabel")
        val dateLabel: String,
        //天気（晴れ、曇り、雨など）
        @ColumnInfo(name = "telop")
        val telop: String,
        //画像
        @ColumnInfo(name = "image_url")
        val imageUrl: String,
        //最高気温
        @ColumnInfo(name = "max_temperature")
        val maxTemperature: Float?,
        //最低気温
        @ColumnInfo(name = "min_temperature")
        val minTemperature: Float?
) {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int = 0
}