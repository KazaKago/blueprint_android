package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Location Model
 *
 * Created by tamura_k on 2016/05/31.
 */
@PaperParcel
class LocationModel : PaperParcelable {

    //地方名（例・九州地方）
    var area: String? = null
    //都道府県名（例・福岡県）
    var prefecture: String? = null
    //1次細分区名（例・八幡）
    var city: String? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelLocationModel.CREATOR
    }

}
