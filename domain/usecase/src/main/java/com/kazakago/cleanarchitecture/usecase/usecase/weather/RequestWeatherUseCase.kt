package com.kazakago.cleanarchitecture.usecase.usecase.weather

import com.kazakago.cleanarchitecture.model.city.CityId

interface RequestWeatherUseCase {

    suspend operator fun invoke(cityId: CityId)

}