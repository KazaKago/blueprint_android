package com.kazakago.cleanarchitecture.domain.usecase;

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel;
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository;

import io.reactivex.Single;

/**
 * Weather UseCase Implement
 *
 * @author Kensuke
 */
public class WeatherUseCaseImpl implements WeatherUseCase {

    private WeatherRepository weatherRepository;

    public WeatherUseCaseImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Single<WeatherModel> fetch(String cityId) {
        return weatherRepository.fetch(cityId);
    }

    @Override
    public WeatherModel find(String cityId) {
        return weatherRepository.find(cityId);
    }

}
