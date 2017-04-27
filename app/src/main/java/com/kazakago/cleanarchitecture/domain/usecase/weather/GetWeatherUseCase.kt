package com.kazakago.cleanarchitecture.domain.usecase.weather

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import com.kazakago.cleanarchitecture.domain.usecase.UseCase

import io.reactivex.Single

/**
 * Weather UseCase
 *
 * @author Kensuke
 */
interface GetWeatherUseCase : UseCase<String, Single<WeatherModel>>