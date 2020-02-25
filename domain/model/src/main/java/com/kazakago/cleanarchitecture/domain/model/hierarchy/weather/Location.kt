package com.kazakago.cleanarchitecture.domain.model.hierarchy.weather

import java.io.Serializable

data class Location(
    val area: String,
    val prefecture: String,
    val city: String
) : Serializable