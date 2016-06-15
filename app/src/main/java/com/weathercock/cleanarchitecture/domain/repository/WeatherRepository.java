package com.weathercock.cleanarchitecture.domain.repository;

import com.weathercock.cleanarchitecture.domain.model.weather.WeatherModel;

import rx.Observable;

/**
 * Weather Repository
 *
 * @author Kensuke
 */
public interface WeatherRepository {

    Observable<WeatherModel> fetch(String cityId);

    WeatherModel find(String cityId);

}
