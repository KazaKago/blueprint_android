package com.kazakago.cleanarchitecture.domain.usecase

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository
import io.reactivex.Single

/**
 * Weather UseCase Implement

 * @author Kensuke
 */
class WeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : WeatherUseCase {

    override fun fetch(cityId: String): Single<WeatherModel> {
        return weatherRepository.fetch(cityId)
                .doOnSuccess {
                    if (weatherRepository.exist(cityId)) {
                        weatherRepository.delete(cityId)
                    }
                    it.cityId = cityId
                    weatherRepository.insert(it)
                }
    }

    override fun find(cityId: String): WeatherModel? {
        return weatherRepository.find(cityId)
    }

}
