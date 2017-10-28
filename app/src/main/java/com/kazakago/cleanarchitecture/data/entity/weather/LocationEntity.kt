package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

open class LocationEntity : RealmObject() {

    //地方名（例・九州地方）
    var area: String? = null
    //都道府県名（例・福岡県）
    var prefecture: String? = null
    //1次細分区名（例・八幡）
    var city: String? = null

}
