package com.kazakago.cleanarchitecture.domain.model.weather

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Link Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class LinkModel : Parcelable {

    //市区町村名
    var name: String? = null
    //対応するlivedoor 天気情報のURL
    var link: String? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelLinkModel.CREATOR
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelLinkModel.writeToParcel(this, dest, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

}
