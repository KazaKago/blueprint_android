package com.kazakago.cleanarchitecture.web.entity.weather

/**
 * Link API Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
data class LinkApiEntity(
        //市区町村名
        var name: String? = null,
        //対応するlivedoor 天気情報のURL
        var link: String? = null
)