package com.ignis.android_cleanarchitecture.domain.usecase;

import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;

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