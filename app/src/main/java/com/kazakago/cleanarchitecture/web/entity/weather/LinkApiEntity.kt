package com.kazakago.cleanarchitecture.web.entity.weather

data class LinkApiEntity(
        //市区町村名
        val name: String,
        //対応するlivedoor 天気情報のURL
        val link: String
)