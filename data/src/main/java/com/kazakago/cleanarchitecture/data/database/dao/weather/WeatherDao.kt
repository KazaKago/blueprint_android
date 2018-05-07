package com.kazakago.cleanarchitecture.data.database.dao.weather

import android.arch.persistence.room.*
import com.kazakago.cleanarchitecture.data.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather WHERE city_id == (:cityId) LIMIT 1")
    fun findWeather(cityId: String): WeatherEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weather: WeatherEntity): Long

    @Delete
    fun delete(weather: WeatherEntity): Int

    @Query("SELECT * FROM Location WHERE city_id == (:cityId) LIMIT 1")
    fun findLocation(cityId: String): LocationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(location: LocationEntity): Long

    @Delete
    fun delete(location: LocationEntity): Int

    @Query("SELECT * FROM Description WHERE city_id == (:cityId) LIMIT 1")
    fun findDescription(cityId: String): DescriptionEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(description: DescriptionEntity): Long

    @Delete
    fun delete(description: DescriptionEntity): Int

    @Query("SELECT * FROM Forecast WHERE city_id == (:cityId)")
    fun findForecasts(cityId: String): List<ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecasts: List<ForecastEntity>): List<Long>

    @Delete
    fun delete(forecasts: List<ForecastEntity>): Int

}
