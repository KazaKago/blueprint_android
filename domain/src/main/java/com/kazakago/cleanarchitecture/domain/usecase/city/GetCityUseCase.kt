package com.kazakago.cleanarchitecture.domain.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.City

interface GetCityUseCase {

    suspend operator fun invoke(): List<City>

}