package com.kazakago.cleanarchitecture.web.entity.weather

/**
 * Weather API Entity
 *
 * Created by tamura_k on 2016/05/31.
 */
data class WeatherApiEntity(
        //予報を発表した地域を定義
        var location: LocationApiEntity? = null,
        //タイトル・見出し
        var title: String? = null,
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        var link: String? = null,
        //予報の発表日時
        var publicTime: String? = null,
        //天気概況文
        var description: DescriptionApiEntity? = null,
        //府県天気予報の予報日毎の配列
        var forecasts: List<ForecastApiEntity> = ArrayList(),
        //ピンポイント予報の発表地点の配列
        var pinpointLocations: List<LinkApiEntity> = ArrayList(),
        //コピーライト
        var copyright: CopyrightApiEntity? = null
)