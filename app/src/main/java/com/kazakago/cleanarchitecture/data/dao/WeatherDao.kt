package com.kazakago.cleanarchitecture.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kazakago.cleanarchitecture.data.entity.weather.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherEntity WHERE cityId == (:cityId)")
    fun find(cityId: String): WeatherEntity?

    fun exist(cityId: String): Boolean {
        return true
    }

    @Insert
    fun insert(weather: WeatherEntity)

    @Delete
    fun delete(weather: WeatherEntity)

}
