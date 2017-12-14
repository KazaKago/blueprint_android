package com.kazakago.cleanarchitecture.data.entity.weather

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class LinkEntity(
        @PrimaryKey(autoGenerate = true)
        val linkId: Int,

        //市区町村名
        val name: String?,
        //対応するlivedoor 天気情報のURL
        val link: String?
)
