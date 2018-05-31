package com.kazakago.cleanarchitecture.data.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kazakago.cleanarchitecture.data.database.dao.weather.WeatherDao
import com.kazakago.cleanarchitecture.data.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.WeatherEntity

@Database(entities = [
    WeatherEntity::class,
    LocationEntity::class,
    ForecastEntity::class,
    DescriptionEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "app-database").build()
        }
    }

    abstract fun weatherDao(): WeatherDao

}
