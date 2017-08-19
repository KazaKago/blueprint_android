package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Image Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
data class ImageModel(
        //天気（晴れ、曇り、雨など）
        val title: String,
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        val link: String,
        //天気アイコンのURL
        val url: String,
        //天気アイコンの幅
        val width: Int,
        //天気アイコンの高さ
        val height: Int
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelImageModel.CREATOR
    }

}
