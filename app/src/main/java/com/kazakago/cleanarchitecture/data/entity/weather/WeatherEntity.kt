package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class WeatherEntity : RealmObject() {

    //地域ID
    @PrimaryKey
    var cityId: String? = null

    //予報を発表した地域を定義
    var location: LocationEntity? = null
    //タイトル・見出し
    var title: String? = null
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    var link: String? = null
    //予報の発表日時
    var publicTime: String? = null
    //天気概況文
    var description: DescriptionEntity? = null
    //府県天気予報の予報日毎の配列
    var forecasts = RealmList<ForecastEntity>()
    //ピンポイント予報の発表地点の配列
    var pinpointLocations = RealmList<LinkEntity>()
    //コピーライト
    var copyright: CopyrightEntity? = null

}
