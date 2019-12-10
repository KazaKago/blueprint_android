package com.kazakago.cleanarchitecture.usecase.usecase.weather

import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.weather.Weather

interface GetWeatherUseCase {

    suspend operator fun invoke(cityId: CityId): Weather

}