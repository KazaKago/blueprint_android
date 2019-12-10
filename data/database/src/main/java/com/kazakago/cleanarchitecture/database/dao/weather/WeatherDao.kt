package com.kazakago.cleanarchitecture.database.dao.weather

import androidx.room.*
import com.kazakago.cleanarchitecture.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.database.entity.weather.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM Weather WHERE city_id == (:cityId) LIMIT 1")
    suspend fun findWeather(cityId: String): WeatherEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(weather: WeatherEntity): Long

    @Delete
    suspend fun delete(weather: WeatherEntity): Int

    @Query("SELECT * FROM Location WHERE city_id == (:cityId) LIMIT 1")
    suspend fun findLocation(cityId: String): LocationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(location: LocationEntity): Long

    @Delete
    suspend fun delete(location: LocationEntity): Int

    @Query("SELECT * FROM Description WHERE city_id == (:cityId) LIMIT 1")
    suspend fun findDescription(cityId: String): DescriptionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(description: DescriptionEntity): Long

    @Delete
    suspend fun delete(description: DescriptionEntity): Int

    @Query("SELECT * FROM Forecast WHERE city_id == (:cityId)")
    suspend fun findForecasts(cityId: String): List<ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(forecasts: List<ForecastEntity>): List<Long>

    @Delete
    suspend fun delete(forecasts: List<ForecastEntity>): Int

}
