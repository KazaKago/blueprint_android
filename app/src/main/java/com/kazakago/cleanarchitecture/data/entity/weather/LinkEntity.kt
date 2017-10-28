package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

open class LinkEntity : RealmObject() {

    //市区町村名
    var name: String? = null
    //対応するlivedoor 天気情報のURL
    var link: String? = null

}
