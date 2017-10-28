package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

open class ImageEntity : RealmObject() {

    //天気（晴れ、曇り、雨など）
    var title: String = ""
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    var link: String = ""
    //天気アイコンのURL
    var url: String = ""
    //天気アイコンの幅
    var width: Int = 0
    //天気アイコンの高さ
    var height: Int = 0

}
