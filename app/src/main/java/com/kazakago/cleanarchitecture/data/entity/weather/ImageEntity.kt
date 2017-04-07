package com.kazakago.cleanarchitecture.data.entity.weather

import android.support.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.Table
import io.realm.RealmObject

/**
 * Image Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
@Table
class ImageEntity : RealmObject() {

    //天気（晴れ、曇り、雨など）
    @Column
    @Nullable
    var title: String? = null
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    @Column
    @Nullable
    var link: String? = null
    //天気アイコンのURL
    @Column
    @Nullable
    var url: String? = null
    //天気アイコンの幅
    @Column
    @Nullable
    var width: Int? = null
    //天気アイコンの高さ
    @Column
    @Nullable
    var height: Int? = null

}
