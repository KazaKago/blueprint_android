package com.kazakago.cleanarchitecture.data.entity.weather

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class WeatherEntity(
    //地域ID
    @PrimaryKey
    val cityId: String,
    //予報を発表した地域を定義
    @Embedded
    val location: LocationEntity?,
    //タイトル・見出し
    val title: String?,
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    val link: String?,
    //予報の発表日時
    val publicTime: String?,
    //天気概況文
    @Embedded
    val description: DescriptionEntity?,
    //府県天気予報の予報日毎の配列
    @Embedded
    val forecasts: List<ForecastEntity>?,
    //ピンポイント予報の発表地点の配列
    @Embedded
    val pinpointLocations: List<LinkEntity>?,
    //コピーライト
    @Embedded
    val copyright: CopyrightEntity?
)