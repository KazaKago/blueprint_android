package com.weathercock.cleanarchitecture.domain.repository;

import com.weathercock.cleanarchitecture.domain.model.weather.WeatherModel;

import rx.Observable;

/**
 * Weather Repository
 *
 * @author Kensuke
 */
public interface WeatherRepository {

    Observable<WeatherModel> fetch(int cityId);

    WeatherModel find(int cityId);

}
