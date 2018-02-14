package com.kazakago.cleanarchitecture.domain.model.city

import java.io.Serializable

data class City(
        val id: String,
        val name: String
) : Serializable