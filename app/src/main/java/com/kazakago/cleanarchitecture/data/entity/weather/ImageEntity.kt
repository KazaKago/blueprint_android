package com.kazakago.cleanarchitecture.data.entity.weather

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class ImageEntity(
        @PrimaryKey(autoGenerate = true)
        val imageId: Int,

        //天気（晴れ、曇り、雨など）
        val title: String?,
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        val link: String?,
        //天気アイコンのURL
        val url: String?,
        //天気アイコンの幅
        val width: Int?,
        //天気アイコンの高さ
        val height: Int?
)