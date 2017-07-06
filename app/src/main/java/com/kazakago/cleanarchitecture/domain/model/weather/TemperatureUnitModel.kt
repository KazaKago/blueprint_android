package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Temperature Unit Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class TemperatureUnitModel : PaperParcelable {

    //摂氏
    var celsius: Float? = null
    //華氏
    var fahrenheit: Float? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelTemperatureUnitModel.CREATOR
    }

}
