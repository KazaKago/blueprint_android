package com.kazakago.cleanarchitecture.domain.model.city

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * City Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class CityModel : Parcelable {

    var id: String? = null
    var name: String? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelCityModel.CREATOR
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelCityModel.writeToParcel(this, dest, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

}
