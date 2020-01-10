package com.kazakago.cleanarchitecture.usecase.output.weather

import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.weather.Weather

data class WeatherOutput(
    val city: City,
    val weather: Weather
)