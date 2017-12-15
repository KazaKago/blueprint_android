package com.kazakago.cleanarchitecture.data.database.entity.weather

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(
    //地域ID
    @PrimaryKey
    @ColumnInfo(name = "city_id")
    val cityId: String,
    //予報を発表した地域を定義
    @ColumnInfo(name = "location")
    val location: LocationEntity,
    //タイトル・見出し
    @ColumnInfo(name = "title")
    val title: String,
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    @ColumnInfo(name = "link")
    val link: String,
    //予報の発表日時
    @ColumnInfo(name = "public_time")
    val publicTime: Long,
    //天気概況文
    @ColumnInfo(name = "description")
    val description: DescriptionEntity,
    //府県天気予報の予報日毎の配列
    @ColumnInfo(name = "forecasts")
    val forecasts: List<ForecastEntity>
)