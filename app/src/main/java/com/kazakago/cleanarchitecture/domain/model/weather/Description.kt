package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.util.*

@PaperParcel
data class Description(
        //天気概況文
        val text: String,
        //天気概況文の発表時刻
        val publicTime: Date
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelDescription.CREATOR
    }

}
