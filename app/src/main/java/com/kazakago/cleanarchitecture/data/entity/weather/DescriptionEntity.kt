package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

/**
 * Description Entity
 *
 * Created by tamura_k on 2016/05/31.
 */
open class DescriptionEntity : RealmObject() {

    //天気概況文
    var text: String? = null
    //天気概況文の発表時刻
    var publicTime: String? = null

}
