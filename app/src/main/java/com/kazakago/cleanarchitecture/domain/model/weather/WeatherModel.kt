package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Weather Model
 *
 * Created by tamura_k on 2016/05/31.
 */
@PaperParcel
data class WeatherModel(
        //地域ID
        val cityId: String,
        //予報を発表した地域を定義
        val location: LocationModel,
        //タイトル・見出し
        val title: String,
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        val link: String,
        //予報の発表日時
        val publicTime: String,
        //天気概況文
        val description: DescriptionModel,
        //府県天気予報の予報日毎の配列
        val forecasts: List<ForecastModel>,
        //ピンポイント予報の発表地点の配列
        val pinpointLocations: List<LinkModel>,
        //コピーライト
        val copyright: CopyrightModel
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelWeatherModel.CREATOR
    }

}
