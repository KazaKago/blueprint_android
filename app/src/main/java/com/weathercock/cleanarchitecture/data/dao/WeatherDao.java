package com.weathercock.cleanarchitecture.data.dao;

import android.support.annotation.NonNull;

import com.weathercock.cleanarchitecture.data.entity.ForecastEntity;
import com.weathercock.cleanarchitecture.data.entity.ImageEntity;
import com.weathercock.cleanarchitecture.data.entity.TemperatureUnitEntity;
import com.weathercock.cleanarchitecture.data.entity.WeatherEntity;

import io.realm.Realm;

/**
 * Weather Dao
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class WeatherDao extends AbsDao {

    public WeatherDao(@NonNull Realm realm) {
        super(realm);
    }

    public WeatherEntity find(int cityId) {
        return getRealm().where(WeatherEntity.class)
                .equalTo("cityId", cityId)
                .findFirst();
    }

    public boolean exist(int cityId) {
        return (0 <= getRealm().where(WeatherEntity.class)
                .equalTo("cityId", cityId)
                .count());
    }

    public void insert(WeatherEntity weather) {
        getRealm().copyToRealm(weather);
    }

    public void delete(int cityId) {
        WeatherEntity weather = find(cityId);
        if (weather != null) {
            for (ForecastEntity forecast : weather.getForecasts()) {
                TemperatureUnitEntity max = forecast.getTemperature().getMax();
                if (max != null) max.deleteFromRealm();
                TemperatureUnitEntity min = forecast.getTemperature().getMin();
                if (min != null) min.deleteFromRealm();
                forecast.getTemperature().deleteFromRealm();
                forecast.getImage().deleteFromRealm();
            }
            weather.getForecasts().deleteAllFromRealm();
            weather.getCopyright().getProvider().deleteAllFromRealm();
            ImageEntity image = weather.getCopyright().getImage();
            if (image != null) image.deleteFromRealm();
            weather.getCopyright().deleteFromRealm();
            weather.getLocation().deleteFromRealm();
            weather.getDescription().deleteFromRealm();
            weather.getPinpointLocations().deleteAllFromRealm();
            weather.deleteFromRealm();
        }
    }

}
