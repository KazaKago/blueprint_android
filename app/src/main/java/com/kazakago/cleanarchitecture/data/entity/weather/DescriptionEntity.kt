package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

open class DescriptionEntity(
        //天気概況文
        var text: String = "",
        //天気概況文の発表時刻
        var publicTime: String = ""
) : RealmObject()