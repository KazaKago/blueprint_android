package com.kazakago.cleanarchitecture.domain.usecase.weather

import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.usecase.UseCase

import io.reactivex.Single

interface GetWeatherUseCase : UseCase<CityId, Single<Weather>>