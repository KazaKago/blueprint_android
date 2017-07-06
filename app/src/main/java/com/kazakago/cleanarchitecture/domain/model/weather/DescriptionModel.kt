package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Description Model
 *
 * Created by tamura_k on 2016/05/31.
 */
@PaperParcel
class DescriptionModel : PaperParcelable {

    //天気概況文
    var text: String? = null
    //天気概況文の発表時刻
    var publicTime: String? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelDescriptionModel.CREATOR
    }

}
