package com.kazakago.cleanarchitecture.domain.model.city

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * City Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class CityModel : PaperParcelable {

    var id: String? = null
    var name: String? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelCityModel.CREATOR
    }

}
