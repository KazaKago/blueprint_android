package com.kazakago.cleanarchitecture.data.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kazakago.cleanarchitecture.data.entity.weather.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
