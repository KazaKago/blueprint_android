package com.kazakago.cleanarchitecture.data.entity.weather

import android.support.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.Table
import io.realm.RealmObject

/**
 * Description Entity
 *
 * Created by tamura_k on 2016/05/31.
 */
@Table
class DescriptionEntity : RealmObject() {

    //天気概況文
    @Column
    @Nullable
    var text: String? = null
    //天気概況文の発表時刻
    @Column
    @Nullable
    var publicTime: String? = null

}
