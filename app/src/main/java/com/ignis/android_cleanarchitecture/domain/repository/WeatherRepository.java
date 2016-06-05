package com.ignis.android_cleanarchitecture.domain.repository;

import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;

import io.realm.Realm;
import rx.Observable;

/**
 * Profile Repository
 *
 * @author Kensuke
 */
public interface WeatherRepository {

    Observable<WeatherModel> fetch(int cityId);

    WeatherModel find(Realm realm, int cityId);

    boolean exist(int cityId);

    void insert(WeatherModel weatherModel);

    void delete(int cityId);

}
