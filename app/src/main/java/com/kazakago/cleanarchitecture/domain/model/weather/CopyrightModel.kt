package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Copyright Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class CopyrightModel : PaperParcelable {

    //コピーライトの文言
    var title: String? = null
    //livedoor 天気情報のURL
    var link: String? = null
    //livedoor 天気情報へのURL、アイコンなど
    var image: ImageModel? = null
    //livedoor 天気情報で使用している気象データの配信元
    var provider: List<LinkModel> = ArrayList()

    companion object {
        @JvmField
        val CREATOR = PaperParcelCopyrightModel.CREATOR
    }

}
