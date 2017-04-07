package com.kazakago.cleanarchitecture.data.entity.weather

import android.support.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.Table
import io.realm.RealmObject

/**
 * Temperature Unit Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
@Table
class TemperatureUnitEntity : RealmObject() {

    //摂氏
    @Column
    @Nullable
    var celsius: Float? = null
    //華氏
    @Column
    @Nullable
    var fahrenheit: Float? = null

}
