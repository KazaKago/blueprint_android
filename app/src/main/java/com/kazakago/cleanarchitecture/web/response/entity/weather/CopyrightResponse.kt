package com.kazakago.cleanarchitecture.web.response.entity.weather

data class CopyrightResponse(
        //コピーライトの文言
        val title: String = "",
        //livedoor 天気情報のURL
        val link: String = "",
        //livedoor 天気情報へのURL、アイコンなど
        val image: ImageResponse = ImageResponse(),
        //livedoor 天気情報で使用している気象データの配信元
        val provider: List<LinkResponse> = listOf()
)