package com.ignis.android_cleanarchitecture.domain.usecase;

import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;
import com.ignis.android_cleanarchitecture.domain.repository.WeatherRepository;

import rx.Observable;

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
    public Observable<WeatherModel> fetch(int cityId) {
        return weatherRepository.fetch(cityId);
    }

    @Override
    public WeatherModel find(int cityId) {
        return weatherRepository.find(cityId);
    }

}
