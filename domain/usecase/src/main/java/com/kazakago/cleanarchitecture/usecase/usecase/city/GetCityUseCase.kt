package com.kazakago.cleanarchitecture.usecase.usecase.city

import com.kazakago.cleanarchitecture.model.city.City

interface GetCityUseCase {

    suspend operator fun invoke(): List<City>

}