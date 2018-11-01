package com.kazakago.cleanarchitecture.domain.model.weather

import java.io.Serializable
import java.util.*

data class Description(
    val text: String,
    val publicTime: Date
) : Serializable