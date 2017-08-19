package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Temperature Unit Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
data class TemperatureUnitModel(
        //摂氏
        val celsius: Float,
        //華氏
        val fahrenheit: Float
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelTemperatureUnitModel.CREATOR
    }

}
