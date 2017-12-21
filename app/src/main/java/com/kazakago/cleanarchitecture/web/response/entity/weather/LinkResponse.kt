package com.kazakago.cleanarchitecture.web.response.entity.weather

data class LinkResponse(
        //市区町村名
        val name: String,
        //対応するlivedoor 天気情報のURL
        val link: String
)