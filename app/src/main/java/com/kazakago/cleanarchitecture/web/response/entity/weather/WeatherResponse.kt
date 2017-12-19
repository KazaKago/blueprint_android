package com.kazakago.cleanarchitecture.web.response.entity.weather

data class WeatherResponse(
        //予報を発表した地域を定義
        val location: LocationResponse = LocationResponse(),
        //タイトル・見出し
        val title: String = "",
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        val link: String = "",
        //予報の発表日時
        val publicTime: String = "",
        //天気概況文
        val description: DescriptionResponse = DescriptionResponse(),
        //府県天気予報の予報日毎の配列
        val forecasts: List<ForecastResponse> = listOf(),
        //ピンポイント予報の発表地点の配列
        val pinpointLocations: List<LinkResponse> = listOf(),
        //コピーライト
        val copyright: CopyrightResponse = CopyrightResponse()
)