package com.kazakago.cleanarchitecture.database.dao.weather

import androidx.room.*
import com.kazakago.cleanarchitecture.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.database.entity.weather.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE city_id == (:cityId) LIMIT 1")
    suspend fun findWeather(cityId: String): WeatherEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity): Long

    @Delete
    suspend fun deleteWeather(weather: WeatherEntity): Int

    @Query("SELECT * FROM location WHERE city_id == (:cityId) LIMIT 1")
    suspend fun findLocation(cityId: String): LocationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocation(location: LocationEntity): Long

    @Delete
    suspend fun deleteLocation(location: LocationEntity): Int

    @Query("SELECT * FROM description WHERE city_id == (:cityId) LIMIT 1")
    suspend fun findDescription(cityId: String): DescriptionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDescription(description: DescriptionEntity): Long

    @Delete
    suspend fun deleteDescription(description: DescriptionEntity): Int

    @Query("SELECT * FROM forecast WHERE city_id == (:cityId)")
    suspend fun findForecasts(cityId: String): List<ForecastEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecasts(forecasts: List<ForecastEntity>): List<Long>

    @Delete
    suspend fun deleteForecasts(forecasts: List<ForecastEntity>): Int

}
