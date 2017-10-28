package com.kazakago.cleanarchitecture.domain.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import com.kazakago.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Observable

interface GetCityUseCase : UseCase<Unit, Observable<CityModel>>