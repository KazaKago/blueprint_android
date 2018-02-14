package com.kazakago.cleanarchitecture.domain.model.weather

import java.io.Serializable

data class Location(
        //地方名（例・九州地方）
        val area: String,
        //都道府県名（例・福岡県）
        val prefecture: String,
        //1次細分区名（例・八幡）
        val city: String
) : Serializable