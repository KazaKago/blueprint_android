package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

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
