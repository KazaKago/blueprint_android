package com.weathercock.android_cleanarchitecture.domain.usecase;

import com.weathercock.android_cleanarchitecture.domain.model.weather.WeatherModel;

import rx.Observable;

/**
 * Weather UseCase
 *
 * @author Kensuke
 */
public interface WeatherUseCase {

    Observable<WeatherModel> fetch(int cityId);

    WeatherModel find(int cityId);

}