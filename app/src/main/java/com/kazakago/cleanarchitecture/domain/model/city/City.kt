package com.kazakago.cleanarchitecture.domain.model.city

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class City(
        val id: String,
        val name: String
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelCityModel.CREATOR
    }

}
