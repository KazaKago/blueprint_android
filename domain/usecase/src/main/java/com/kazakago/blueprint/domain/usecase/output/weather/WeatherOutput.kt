package com.kazakago.blueprint.domain.usecase.output.weather

import com.kazakago.blueprint.domain.model.hierarchy.city.City
import com.kazakago.blueprint.domain.model.hierarchy.weather.Weather
import java.io.Serializable

data class WeatherOutput(
    val city: City,
    val weather: Weather
) : Serializable
