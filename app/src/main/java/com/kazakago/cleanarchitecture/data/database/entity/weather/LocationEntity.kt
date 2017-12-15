package com.kazakago.cleanarchitecture.data.database.entity.weather

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "location",
        foreignKeys = [ForeignKey(entity = WeatherEntity::class,
                parentColumns = ["city_id"],
                childColumns = ["id"],
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE)])
data class LocationEntity(
        //地方名（例・九州地方）
        @ColumnInfo(name = "area")
        val area: String,
        //都道府県名（例・福岡県）
        @ColumnInfo(name = "prefecture")
        val prefecture: String,
        //1次細分区名（例・八幡）
        @ColumnInfo(name = "city")
        val city: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}