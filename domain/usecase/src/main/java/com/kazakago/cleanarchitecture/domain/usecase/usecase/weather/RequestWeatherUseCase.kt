package com.kazakago.cleanarchitecture.domain.usecase.usecase.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId

interface RequestWeatherUseCase {

    suspend operator fun invoke(cityId: CityId)

}