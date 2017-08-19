package com.kazakago.cleanarchitecture.data.entity.city

import com.squareup.moshi.Json

/**
 * City Entity.
 *
 * Created by tamura_k on 2016/06/03.
 */
data class CityEntity(
        @Json(name = "id")
        val id: String,
        @Json(name = "title")
        val title: String
)
