package com.kazakago.cleanarchitecture.data.database.entity.weather

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "location",
        foreignKeys = [ForeignKey(entity = WeatherEntity::class,
                parentColumns = ["city_id"],
                childColumns = ["city_id"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)])
data class LocationEntity(
        @ColumnInfo(name = "city_id")
        var cityId: String,

        @ColumnInfo(name = "area")
        val area: String,
        @ColumnInfo(name = "prefecture")
        val prefecture: String,
        @ColumnInfo(name = "city")
        val city: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}