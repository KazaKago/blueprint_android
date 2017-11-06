package com.kazakago.cleanarchitecture.web.entity.weather

data class CopyrightApiEntity(
        //コピーライトの文言
        val title: String = "",
        //livedoor 天気情報のURL
        val link: String = "",
        //livedoor 天気情報へのURL、アイコンなど
        val image: ImageApiEntity = ImageApiEntity(),
        //livedoor 天気情報で使用している気象データの配信元
        val provider: List<LinkApiEntity> = listOf()
)