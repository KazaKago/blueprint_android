package com.kazakago.cleanarchitecture.domain.usecase;

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel;

import io.reactivex.Single;

/**
 * Weather UseCase
 *
 * @author Kensuke
 */
public interface WeatherUseCase {

    Single<WeatherModel> fetch(String cityId);

    WeatherModel find(String cityId);

}