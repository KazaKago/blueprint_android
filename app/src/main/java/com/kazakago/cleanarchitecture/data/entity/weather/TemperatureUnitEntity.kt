package com.kazakago.cleanarchitecture.data.entity.weather

import io.realm.RealmObject

/**
 * Temperature Unit Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
open class TemperatureUnitEntity : RealmObject() {

    //摂氏
    var celsius: Float? = null
    //華氏
    var fahrenheit: Float? = null

}
