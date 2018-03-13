package com.kazakago.cleanarchitecture.domain.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Single

interface GetCityUseCase : UseCase<Unit, Single<List<City>>>