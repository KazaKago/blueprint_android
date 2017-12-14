package com.kazakago.cleanarchitecture.data.entity.weather

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class DescriptionEntity(
        @PrimaryKey(autoGenerate = true)
        val descriptionId: Int,

        //天気概況文
        val text: String?,
        //天気概況文の発表時刻
        val publicTime: String?
)