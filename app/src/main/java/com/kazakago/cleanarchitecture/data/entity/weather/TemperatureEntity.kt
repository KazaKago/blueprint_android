package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

/**
 * Temperature Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
open class TemperatureEntity : RealmObject() {

    //最高気温
    var max: TemperatureUnitEntity? = null
    //最低気温
    var min: TemperatureUnitEntity? = null

}
