package com.ignis.android_cleanarchitecture.domain.repository;

import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;

import rx.Observable;

/**
 * Profile Repository
 *
 * @author Kensuke
 */
public interface WeatherRepository {

    Observable<WeatherModel> fetch(int cityId);

    WeatherModel find(int cityId);

}
