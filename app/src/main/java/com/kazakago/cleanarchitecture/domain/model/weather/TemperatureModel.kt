package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Temperature Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class TemperatureModel : PaperParcelable {

    //最高気温
    var max: TemperatureUnitModel? = null
    //最低気温
    var min: TemperatureUnitModel? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelTemperatureModel.CREATOR
    }

}
