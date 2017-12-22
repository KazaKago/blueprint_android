package com.kazakago.cleanarchitecture.domain.usecase.weather

import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherApiRepository
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class GetWeatherUseCaseImpl(private val weatherApiRepository: WeatherApiRepository, private val weatherRepository: WeatherRepository) : GetWeatherUseCase {

    override fun execute(input: String): Single<Weather> {
        return Single.create<Weather> { subscriber ->
            weatherApiRepository.fetch(input).subscribeBy(
                    onSuccess = {
                        it.cityId = input
                        weatherRepository.find(input)?.let {
                            weatherRepository.delete(it)
                        }
                        weatherRepository.insert(it)
                        subscriber.onSuccess(it)
                    },
                    onError = {
                        weatherRepository.find(input)?.let {
                            subscriber.onSuccess(it)
                        } ?: subscriber.onError(it)
                    })
        }
    }

}
