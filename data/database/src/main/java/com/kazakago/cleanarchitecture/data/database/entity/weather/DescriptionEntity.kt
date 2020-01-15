package com.kazakago.cleanarchitecture.data.database.entity.weather

import androidx.room.*

@Entity(
    tableName = "description",
    indices = [Index(value = ["city_id"])],
    foreignKeys = [ForeignKey(
        entity = WeatherEntity::class,
        parentColumns = ["city_id"],
        childColumns = ["city_id"],
        onUpdate = ForeignKey.CASCADE,
        onDelete = ForeignKey.CASCADE
    )]
)
data class DescriptionEntity(
    @ColumnInfo(name = "city_id")
    val cityId: String,

    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "public_time")
    val publicTime: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}