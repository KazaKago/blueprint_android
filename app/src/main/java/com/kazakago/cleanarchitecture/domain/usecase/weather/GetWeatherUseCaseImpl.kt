package com.kazakago.cleanarchitecture.domain.usecase.weather

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel
import com.kazakago.cleanarchitecture.domain.repository.WeatherApiRepository
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy

class GetWeatherUseCaseImpl(private val weatherApiRepository: WeatherApiRepository, private val weatherRepository: WeatherRepository) : GetWeatherUseCase {

    override fun execute(input: String): Single<WeatherModel> {
        return Single.create<WeatherModel> { subscriber ->
            weatherApiRepository.fetch(input).subscribeBy(
                    onSuccess = {
                        it.cityId = input
                        if (weatherRepository.exist(cityId = input)) {
                            weatherRepository.delete(cityId = input)
                        }
                        weatherRepository.insert(weather = it)
                        subscriber.onSuccess(it)
                    },
                    onError = {
                        weatherRepository.find(cityId = input)?.let {
                            subscriber.onSuccess(it)
                        } ?: subscriber.onError(it)
                    })
        }
    }

}
