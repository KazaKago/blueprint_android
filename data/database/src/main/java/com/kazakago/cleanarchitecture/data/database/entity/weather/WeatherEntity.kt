package com.kazakago.cleanarchitecture.data.database.entity.weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Duration
import java.time.LocalDateTime

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey
    @ColumnInfo(name = "city_id")
    val cityId: String,

    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "link")
    val link: String,
    @ColumnInfo(name = "public_time")
    val publicTime: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String = LocalDateTime.now().toString()
) {

    companion object {
        private val EXPIRED = Duration.ofHours(1)
    }

    fun isExpired(): Boolean {
        val createdAt = LocalDateTime.parse(createdAt)
        return ((createdAt + EXPIRED) <= LocalDateTime.now())
    }

}