package com.kazakago.cleanarchitecture.domain.model.weather

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

@PaperParcel
data class ForecastModel(
        //予報日
        var date: String,
        //予報日(今日、明日、明後日のいずれか)
        var dateLabel: String,
        //天気（晴れ、曇り、雨など）
        var telop: String,
        //画像
        var image: ImageModel,
        //気温
        var temperature: TemperatureModel
) : PaperParcelable {

    companion object {
        @JvmField
        val CREATOR = PaperParcelForecastModel.CREATOR
    }

}
