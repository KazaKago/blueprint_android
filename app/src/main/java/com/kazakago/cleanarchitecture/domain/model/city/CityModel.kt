package com.kazakago.cleanarchitecture.domain.model.city

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * City Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
data class CityModel(
        val id: String,
        val name: String
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelCityModel.CREATOR
    }

}
