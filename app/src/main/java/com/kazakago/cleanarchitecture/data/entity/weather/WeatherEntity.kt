package com.kazakago.cleanarchitecture.data.entity.weather

import android.support.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Table
import io.realm.OrderedRealmCollection
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Weather Entity
 *
 * Created by tamura_k on 2016/05/31.
 */
@Table
class WeatherEntity : RealmObject() {

    //地域ID
    @PrimaryKey
    var cityId: String? = null

    //予報を発表した地域を定義
    @Column
    @Nullable
    var location: LocationEntity? = null
    //タイトル・見出し
    @Column
    @Nullable
    var title: String? = null
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    @Column
    @Nullable
    var link: String? = null
    //予報の発表日時
    @Column
    @Nullable
    var publicTime: String? = null
    //天気概況文
    @Column
    @Nullable
    var description: DescriptionEntity? = null
    //府県天気予報の予報日毎の配列
    @Column
    var forecasts: OrderedRealmCollection<ForecastEntity> = RealmList()
    //ピンポイント予報の発表地点の配列
    @Column
    var pinpointLocations: OrderedRealmCollection<LinkEntity> = RealmList()
    //コピーライト
    @Column
    @Nullable
    var copyright: CopyrightEntity? = null

}
