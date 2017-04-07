package com.kazakago.cleanarchitecture.domain.model.weather

/**
 * Forecasts Model
 *
 * Created by tamura_k on 2016/06/03.
 */
class ForecastModel {

    //予報日
    var date: String? = null
    //予報日(今日、明日、明後日のいずれか)
    var dateLabel: String? = null
    //天気（晴れ、曇り、雨など）
    var telop: String? = null
    //画像
    var image: ImageModel? = null
    //気温
    var temperature: TemperatureModel? = null

}
