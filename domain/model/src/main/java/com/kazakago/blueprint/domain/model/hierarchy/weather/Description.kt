package com.kazakago.blueprint.domain.model.hierarchy.weather

import java.io.Serializable
import java.time.LocalDateTime

data class Description(
    val text: String,
    val publicTime: LocalDateTime
) : Serializable
