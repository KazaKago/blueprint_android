package com.kazakago.cleanarchitecture.domain.repository;

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel;

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
