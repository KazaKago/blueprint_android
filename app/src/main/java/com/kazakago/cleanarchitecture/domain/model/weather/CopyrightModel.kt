package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class CopyrightModel(
        //コピーライトの文言
        val title: String,
        //livedoor 天気情報のURL
        val link: String,
        //livedoor 天気情報へのURL、アイコンなど
        val image: ImageModel,
        //livedoor 天気情報で使用している気象データの配信元
        val provider: List<LinkModel>
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelCopyrightModel.CREATOR
    }

}
