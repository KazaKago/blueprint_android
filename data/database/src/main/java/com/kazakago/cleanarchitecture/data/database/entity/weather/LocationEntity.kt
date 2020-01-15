package com.kazakago.cleanarchitecture.data.database.entity.weather

import androidx.room.*

@Entity(
    tableName = "location",
    indices = [Index(value = ["city_id"])],
    foreignKeys = [ForeignKey(
        entity = WeatherEntity::class,
        parentColumns = ["city_id"],
        childColumns = ["city_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class LocationEntity(
    @ColumnInfo(name = "city_id")
    val cityId: String,

    @ColumnInfo(name = "area")
    val area: String,
    @ColumnInfo(name = "prefecture")
    val prefecture: String,
    @ColumnInfo(name = "city")
    val city: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}