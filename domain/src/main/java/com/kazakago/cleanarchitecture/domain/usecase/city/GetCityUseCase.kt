package com.kazakago.cleanarchitecture.domain.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.SuspendingUseCase

interface GetCityUseCase : SuspendingUseCase<Unit, List<City>>