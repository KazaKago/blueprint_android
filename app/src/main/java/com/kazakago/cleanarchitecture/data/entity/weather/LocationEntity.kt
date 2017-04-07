package com.kazakago.cleanarchitecture.data.entity.weather

import android.support.annotation.Nullable
import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.Table
import io.realm.RealmObject

/**
 * Location Entity
 *
 * Created by tamura_k on 2016/05/31.
 */
@Table
class LocationEntity : RealmObject() {

    //地方名（例・九州地方）
    @Column
    @Nullable
    var area: String? = null
    //都道府県名（例・福岡県）
    @Column
    @Nullable
    var prefecture: String? = null
    //1次細分区名（例・八幡）
    @Column
    @Nullable
    var city: String? = null

}
