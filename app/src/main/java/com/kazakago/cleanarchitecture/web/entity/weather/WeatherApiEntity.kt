package com.kazakago.cleanarchitecture.web.entity.weather

data class WeatherApiEntity(
        //予報を発表した地域を定義
        val location: LocationApiEntity = LocationApiEntity(),
        //タイトル・見出し
        val title: String = "",
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        val link: String = "",
        //予報の発表日時
        val publicTime: String = "",
        //天気概況文
        val description: DescriptionApiEntity = DescriptionApiEntity(),
        //府県天気予報の予報日毎の配列
        val forecasts: List<ForecastApiEntity> = listOf(),
        //ピンポイント予報の発表地点の配列
        val pinpointLocations: List<LinkApiEntity> = listOf(),
        //コピーライト
        val copyright: CopyrightApiEntity = CopyrightApiEntity()
)