package com.ignis.android_cleanarchitecture.data.dao;

import android.support.annotation.NonNull;

import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;

import io.realm.Realm;
import rx.Observable;

/**
 * Weather Dao
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class WeatherDao extends AbsDao {

    public WeatherDao(@NonNull Realm realm) {
        super(realm);
    }

    public WeatherModel find(int cityId) {
        return getRealm().where(WeatherModel.class)
                .equalTo("cityId", cityId)
                .findFirst();
    }

    public boolean isExist(int cityId) {
        return (0 <= getRealm().where(WeatherModel.class)
                .equalTo("cityId", cityId)
                .count());
    }

    public void save(WeatherModel weatherModel) {
        getRealm().copyToRealmOrUpdate(weatherModel);
    }

    public void delete(int cityId) {
        WeatherModel weatherModel = getRealm().where(WeatherModel.class)
                .equalTo("cityId", cityId)
                .findFirst();
        if (weatherModel != null) {
            Observable.from(weatherModel.getForecasts())
                    .subscribe(forecastsModel -> {
                        forecastsModel.getImage().deleteFromRealm();
                        forecastsModel.getTemperature().deleteFromRealm();
                    });
            weatherModel.getForecasts().deleteAllFromRealm();
            weatherModel.getCopyright().getProvider().deleteAllFromRealm();
            weatherModel.getCopyright().deleteFromRealm();
            weatherModel.getLocation().deleteFromRealm();
            weatherModel.getDescription().deleteFromRealm();
            weatherModel.getPinpointLocation().deleteAllFromRealm();
            weatherModel.deleteFromRealm();
        }
    }

}
