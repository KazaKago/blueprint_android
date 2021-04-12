package com.kazakago.blueprint.domain.model.hierarchy.city

import java.io.Serializable

data class City(
    val id: CityId,
    val name: String
) : Serializable
