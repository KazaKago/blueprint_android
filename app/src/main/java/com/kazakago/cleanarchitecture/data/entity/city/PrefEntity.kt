package com.kazakago.cleanarchitecture.data.entity.city

import com.squareup.moshi.Json

/**
 * Pref Entity.
 *
 * Created by weath on 2016/06/14.
 */
data class PrefEntity(
        @Json(name = "title")
        val title: String,
        @Json(name = "city")
        val cityList: List<CityEntity>
)
