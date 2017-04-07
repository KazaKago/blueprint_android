package com.kazakago.cleanarchitecture.data.entity.weather

import android.support.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.Table
import io.realm.RealmObject

/**
 * Link Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
@Table
class LinkEntity : RealmObject() {

    //市区町村名
    @Column
    @Nullable
    var name: String? = null
    //対応するlivedoor 天気情報のURL
    @Column
    @Nullable
    var link: String? = null

}
