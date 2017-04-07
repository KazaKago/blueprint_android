package com.kazakago.cleanarchitecture.data.entity.weather

import android.support.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.Table
import io.realm.RealmObject

/**
 * Temperature Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
@Table
class TemperatureEntity : RealmObject() {

    //最高気温
    @Column
    @Nullable
    var max: TemperatureUnitEntity? = null
    //最低気温
    @Column
    @Nullable
    var min: TemperatureUnitEntity? = null

}
