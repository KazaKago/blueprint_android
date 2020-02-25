package com.kazakago.cleanarchitecture.domain.usecase.output.weather

import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Weather

data class WeatherOutput(
    val city: City,
    val weather: Weather
)