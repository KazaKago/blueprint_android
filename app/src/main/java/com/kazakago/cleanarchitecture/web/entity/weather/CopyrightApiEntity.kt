package com.kazakago.cleanarchitecture.web.entity.weather

/**
 * Copyright API Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
data class CopyrightApiEntity(
        //コピーライトの文言
        var title: String? = null,
        //livedoor 天気情報のURL
        var link: String? = null,
        //livedoor 天気情報へのURL、アイコンなど
        var image: ImageApiEntity? = null,
        //livedoor 天気情報で使用している気象データの配信元
        var provider: List<LinkApiEntity> = ArrayList()
)