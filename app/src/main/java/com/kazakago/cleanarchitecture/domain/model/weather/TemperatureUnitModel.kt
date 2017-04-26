package com.kazakago.cleanarchitecture.domain.model.weather

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Temperature Unit Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class TemperatureUnitModel : Parcelable {

    //摂氏
    var celsius: Float? = null
    //華氏
    var fahrenheit: Float? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelTemperatureUnitModel.CREATOR
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelTemperatureUnitModel.writeToParcel(this, dest, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

}
