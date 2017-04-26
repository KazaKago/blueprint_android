package com.kazakago.cleanarchitecture.domain.model.weather

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Description Model
 *
 * Created by tamura_k on 2016/05/31.
 */
@PaperParcel
class DescriptionModel : Parcelable {

    //天気概況文
    var text: String? = null
    //天気概況文の発表時刻
    var publicTime: String? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelDescriptionModel.CREATOR
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelDescriptionModel.writeToParcel(this, dest, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

}
