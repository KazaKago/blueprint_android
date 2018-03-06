package com.kazakago.cleanarchitecture.domain.model.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import java.io.Serializable
import java.net.URL
import java.util.*

data class Weather(
        //予報を発表した地域を定義
        val location: Location,
        //タイトル・見出し
        val title: String,
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        val link: URL,
        //予報の発表日時
        val publicTime: Date,
        //天気概況文
        val description: Description,
        //府県天気予報の予報日毎の配列
        val forecasts: List<Forecast>
) : Serializable {
    //地域ID
    lateinit var cityId: CityId
}
