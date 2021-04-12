package com.kazakago.blueprint.domain.usecase.hierarchy.weather

import com.kazakago.blueprint.domain.model.hierarchy.city.CityId

interface RequestWeatherUseCase {

    suspend operator fun invoke(cityId: CityId)
}
