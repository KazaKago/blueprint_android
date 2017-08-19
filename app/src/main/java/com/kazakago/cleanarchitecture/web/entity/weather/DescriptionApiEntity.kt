package com.kazakago.cleanarchitecture.web.entity.weather

/**
 * Description API Entity
 *
 * Created by tamura_k on 2016/05/31.
 */
data class DescriptionApiEntity(
        //天気概況文
        val text: String,
        //天気概況文の発表時刻
        val publicTime: String
)