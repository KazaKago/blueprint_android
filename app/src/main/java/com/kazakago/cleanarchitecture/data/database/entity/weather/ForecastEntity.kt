package com.kazakago.cleanarchitecture.data.database.entity.weather

import android.arch.persistence.room.*

@Entity(tableName = "forecast",
        indices = [(Index(value = ["city_id"]))],
        foreignKeys = [(ForeignKey(entity = WeatherEntity::class,
                parentColumns = ["city_id"],
                childColumns = ["city_id"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE))])
data class ForecastEntity(
        @ColumnInfo(name = "city_id")
        var cityId: String,

        @ColumnInfo(name = "date")
        val date: Long,
        @ColumnInfo(name = "dateLabel")
        val dateLabel: String,
        @ColumnInfo(name = "telop")
        val telop: String,
        @ColumnInfo(name = "image_url")
        val imageUrl: String,
        @ColumnInfo(name = "max_temperature")
        val maxTemperature: Float?,
        @ColumnInfo(name = "min_temperature")
        val minTemperature: Float?
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}