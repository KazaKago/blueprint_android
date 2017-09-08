package com.kazakago.cleanarchitecture.data.entity.weather

import com.kazakago.cleanarchitecture.domain.model.weather.*
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Weather Entity
 *
 * Created by tamura_k on 2016/05/31.
 */
open class WeatherEntity(
        //地域ID
        @PrimaryKey
        val cityId: String,
        //予報を発表した地域を定義
        val location: LocationModel,
        //タイトル・見出し
        val title: String,
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        val link: String,
        //予報の発表日時
        val publicTime: String,
        //天気概況文
        val description: DescriptionModel,
        //府県天気予報の予報日毎の配列
        val forecasts: List<ForecastModel>,
        //ピンポイント予報の発表地点の配列
        val pinpointLocations: List<LinkModel>,
        //コピーライト
        val copyright: CopyrightModel
) : RealmObject() {

//    //地域ID
//    @PrimaryKey
//    var cityId: String
//
//    //予報を発表した地域を定義
//    var location: LocationEntity? = null
//    //タイトル・見出し
//    var title: String? = null
//    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
//    var link: String? = null
//    //予報の発表日時
//    var publicTime: String? = null
//    //天気概況文
//    var description: DescriptionEntity? = null
//    //府県天気予報の予報日毎の配列
//    var forecasts = RealmList<ForecastEntity>()
//    //ピンポイント予報の発表地点の配列
//    var pinpointLocations = RealmList<LinkEntity>()
//    //コピーライト
//    var copyright: CopyrightEntity? = null

}
