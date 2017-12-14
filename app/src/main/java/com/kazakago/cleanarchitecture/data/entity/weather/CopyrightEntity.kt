package com.kazakago.cleanarchitecture.data.entity.weather

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class CopyrightEntity(
        @PrimaryKey(autoGenerate = true)
        val copyrightId: Int,

        //コピーライトの文言
        val title: String?,
        //livedoor 天気情報のURL
        val link: String?,
        //livedoor 天気情報へのURL、アイコンなど
        @Embedded
        val image: ImageEntity?,
        //livedoor 天気情報で使用している気象データの配信元
        @Embedded
        val provider: List<LinkEntity>?
)
