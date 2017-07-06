package com.kazakago.cleanarchitecture.domain.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import com.kazakago.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Observable

/**
 * Created by tamura_k on 2017/04/27.
 */
interface GetCityUseCase : UseCase<Unit, Observable<CityModel>>