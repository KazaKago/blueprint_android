package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Description Model
 *
 * Created by tamura_k on 2016/05/31.
 */
@PaperParcel
data class DescriptionModel(
        //天気概況文
        val text: String,
        //天気概況文の発表時刻
        val publicTime: String
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelDescriptionModel.CREATOR
    }

}
