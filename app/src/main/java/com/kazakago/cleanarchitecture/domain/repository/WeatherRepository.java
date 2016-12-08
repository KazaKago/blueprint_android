package com.kazakago.cleanarchitecture.domain.repository;

import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel;

import io.reactivex.Single;

/**
 * Weather Repository
 *
 * @author Kensuke
 */
public interface WeatherRepository {

    Single<WeatherModel> fetch(String cityId);

    WeatherModel find(String cityId);

}
