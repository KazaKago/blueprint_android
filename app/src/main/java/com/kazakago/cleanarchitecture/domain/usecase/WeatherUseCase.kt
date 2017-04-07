package com.kazakago.cleanarchitecture.domain.usecase

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel

import io.reactivex.Single

/**
 * Weather UseCase

 * @author Kensuke
 */
interface WeatherUseCase {

    fun fetch(cityId: String): Single<WeatherModel>

    fun find(cityId: String): WeatherModel?

}