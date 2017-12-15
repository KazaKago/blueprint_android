package com.kazakago.cleanarchitecture.data.database.dao.weather

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kazakago.cleanarchitecture.data.database.entity.weather.DescriptionEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.ForecastEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.LocationEntity
import com.kazakago.cleanarchitecture.data.database.entity.weather.WeatherEntity

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherEntity WHERE cityId == (:cityId)")
    fun findWeather(cityId: String): WeatherEntity?

    @Insert
    fun insertWeather(weather: WeatherEntity)

    @Delete
    fun deleteWeather(weather: WeatherEntity)

    @Query("SELECT * FROM LocationEntity WHERE id == (:id)")
    fun findLocation(id: Long): LocationEntity?

    @Insert
    fun insertLocation(location: LocationEntity)

    @Delete
    fun deleteLocation(location: LocationEntity)

    @Query("SELECT * FROM ForecastEntity WHERE id == (:id)")
    fun findForecast(id: Long): ForecastEntity?

    @Insert
    fun insertForecast(forecast: ForecastEntity)

    @Delete
    fun deleteForecast(forecast: ForecastEntity)

    @Query("SELECT * FROM DescriptionEntity WHERE id == (:id)")
    fun findDescription(id: Long): DescriptionEntity?

    @Insert
    fun insertDescription(description: DescriptionEntity)

    @Delete
    fun deleteDescription(description: DescriptionEntity)

}
