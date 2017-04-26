package com.kazakago.cleanarchitecture.domain.model.weather

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Copyright Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class CopyrightModel : Parcelable {

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

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelCopyrightModel.writeToParcel(this, dest, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

}
