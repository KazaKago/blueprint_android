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
    public Observable<WeatherModel> download(int cityId) {
        return weatherRepository.fetch(cityId)
                .map(weatherModel -> {
                    if (weatherRepository.exist(cityId)) {
                        weatherRepository.delete(cityId);
                    }
                    weatherModel.setCityId(cityId);
                    weatherRepository.insert(weatherModel);
                    return weatherModel;
                });
    }

}
