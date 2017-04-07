package com.kazakago.cleanarchitecture.data.entity.weather

import android.support.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.Table
import io.realm.RealmObject

/**
 * Forecasts Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
@Table
class ForecastEntity : RealmObject() {

    //予報日
    @Column
    @Nullable
    var date: String? = null
    //予報日(今日、明日、明後日のいずれか)
    @Column
    @Nullable
    var dateLabel: String? = null
    //天気（晴れ、曇り、雨など）
    @Column
    @Nullable
    var telop: String? = null
    //画像
    @Column
    @Nullable
    var image: ImageEntity? = null
    //気温
    @Column
    @Nullable
    var temperature: TemperatureEntity? = null

}
