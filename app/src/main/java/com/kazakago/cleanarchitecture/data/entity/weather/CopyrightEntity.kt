package com.kazakago.cleanarchitecture.data.entity.weather

import android.support.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.Table
import io.realm.OrderedRealmCollection
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Copyright Entity
 *
 * Created by tamura_k on 2016/06/03.
 */
@Table
class CopyrightEntity : RealmObject() {

    //コピーライトの文言
    @Column
    @Nullable
    var title: String? = null
    //livedoor 天気情報のURL
    @Column
    @Nullable
    var link: String? = null
    //livedoor 天気情報へのURL、アイコンなど
    @Column
    @Nullable
    var image: ImageEntity? = null
    //livedoor 天気情報で使用している気象データの配信元
    @Column
    var provider: OrderedRealmCollection<LinkEntity> = RealmList()

}
