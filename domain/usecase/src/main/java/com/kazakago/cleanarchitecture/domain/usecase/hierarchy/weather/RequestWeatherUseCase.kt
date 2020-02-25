package com.kazakago.cleanarchitecture.domain.usecase.hierarchy.weather

import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId

interface RequestWeatherUseCase {

    suspend operator fun invoke(cityId: CityId)

}