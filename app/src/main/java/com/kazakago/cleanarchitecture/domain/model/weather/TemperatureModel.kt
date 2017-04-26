package com.kazakago.cleanarchitecture.domain.model.weather

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Temperature Model
 *
 * Created by tamura_k on 2016/06/03.
 */
@PaperParcel
class TemperatureModel : Parcelable {

    //最高気温
    var max: TemperatureUnitModel? = null
    //最低気温
    var min: TemperatureUnitModel? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelTemperatureModel.CREATOR
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelTemperatureModel.writeToParcel(this, dest, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

}
