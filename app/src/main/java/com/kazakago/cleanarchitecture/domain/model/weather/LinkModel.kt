package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Link Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class LinkModel : PaperParcelable {

    //市区町村名
    var name: String? = null
    //対応するlivedoor 天気情報のURL
    var link: String? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelLinkModel.CREATOR
    }

}
