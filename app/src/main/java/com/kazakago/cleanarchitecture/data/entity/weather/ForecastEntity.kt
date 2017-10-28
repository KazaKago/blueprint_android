package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

open class ForecastEntity : RealmObject() {

    //予報日
    var date: String = ""
    //予報日(今日、明日、明後日のいずれか)
    var dateLabel: String = ""
    //天気（晴れ、曇り、雨など）
    var telop: String = ""
    //画像
    var image: ImageEntity? = null
    //気温
    var temperature: TemperatureEntity? = null

}
