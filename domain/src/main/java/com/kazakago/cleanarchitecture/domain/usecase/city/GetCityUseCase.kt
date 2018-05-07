package com.kazakago.cleanarchitecture.domain.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.UseCase

interface GetCityUseCase : UseCase<Unit, List<City>>