package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class TemperatureModel(
        //最高気温
        val max: TemperatureUnitModel?,
        //最低気温
        val min: TemperatureUnitModel?
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelTemperatureModel.CREATOR
    }

}
