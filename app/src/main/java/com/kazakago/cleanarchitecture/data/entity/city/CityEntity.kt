package com.kazakago.cleanarchitecture.data.entity.city

import com.google.gson.annotations.SerializedName

/**
 * City Entity.
 *
 * Created by tamura_k on 2016/06/03.
 */
class CityEntity {

    @SerializedName("id")
    var id: String? = null
    @SerializedName("title")
    var title: String? = null

}
