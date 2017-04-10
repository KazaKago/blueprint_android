package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

/**
 * Link Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
open class LinkEntity : RealmObject() {

    //市区町村名
    var name: String? = null
    //対応するlivedoor 天気情報のURL
    var link: String? = null

}
