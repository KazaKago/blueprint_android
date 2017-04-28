package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Weather Model
 *
 * Created by tamura_k on 2016/05/31.
 */
@PaperParcel
class WeatherModel : PaperParcelable {

    //地域ID
    var cityId: String? = null

    //予報を発表した地域を定義
    var location: LocationModel? = null
    //タイトル・見出し
    var title: String? = null
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    var link: String? = null
    //予報の発表日時
    var publicTime: String? = null
    //天気概況文
    var description: DescriptionModel? = null
    //府県天気予報の予報日毎の配列
    var forecasts: List<ForecastModel> = ArrayList()
    //ピンポイント予報の発表地点の配列
    var pinpointLocations: List<LinkModel> = ArrayList()
    //コピーライト
    var copyright: CopyrightModel? = null

    companion object {
        @JvmField
        val CREATOR = PaperParcelWeatherModel.CREATOR
    }

}
