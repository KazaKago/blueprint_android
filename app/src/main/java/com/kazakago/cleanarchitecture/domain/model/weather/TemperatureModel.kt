package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Temperature Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
data class TemperatureModel(
        //最高気温
        val max: TemperatureUnitModel,
        //最低気温
        val min: TemperatureUnitModel
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelTemperatureModel.CREATOR
    }

}
