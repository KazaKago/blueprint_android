package com.kazakago.cleanarchitecture.domain.usecase;

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel;

import rx.Observable;

/**
 * Weather UseCase
 *
 * @author Kensuke
 */
public interface WeatherUseCase {

    Observable<WeatherModel> fetch(String cityId);

    WeatherModel find(String cityId);

}