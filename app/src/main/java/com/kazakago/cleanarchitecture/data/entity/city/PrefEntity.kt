package com.kazakago.cleanarchitecture.data.entity.city

import com.google.gson.annotations.SerializedName

/**
 * Pref Entity.
 *
 * Created by weath on 2016/06/14.
 */
class PrefEntity {

    @SerializedName("title")
    var title: String? = null
    @SerializedName("city")
    var cityList: List<CityEntity> = ArrayList()

}
