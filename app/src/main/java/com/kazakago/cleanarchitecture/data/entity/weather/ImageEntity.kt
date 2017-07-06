package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

/**
 * Image Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
open class ImageEntity : RealmObject() {

    //天気（晴れ、曇り、雨など）
    var title: String? = null
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    var link: String? = null
    //天気アイコンのURL
    var url: String? = null
    //天気アイコンの幅
    var width: Int? = null
    //天気アイコンの高さ
    var height: Int? = null

}
