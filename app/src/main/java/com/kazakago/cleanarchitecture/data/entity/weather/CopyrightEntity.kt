package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Copyright Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
open class CopyrightEntity : RealmObject() {

    //コピーライトの文言
    var title: String? = null
    //livedoor 天気情報のURL
    var link: String? = null
    //livedoor 天気情報へのURL、アイコンなど
    var image: ImageEntity? = null
    //livedoor 天気情報で使用している気象データの配信元
    var provider = RealmList<LinkEntity>()

}
