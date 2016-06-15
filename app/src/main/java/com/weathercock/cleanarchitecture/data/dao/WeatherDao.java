package com.weathercock.cleanarchitecture.data.dao;

import com.weathercock.cleanarchitecture.data.entity.weather.ForecastEntity;
import com.weathercock.cleanarchitecture.data.entity.weather.ImageEntity;
import com.weathercock.cleanarchitecture.data.entity.weather.TemperatureUnitEntity;
import com.weathercock.cleanarchitecture.data.entity.weather.WeatherEntity;

import io.realm.Realm;

/**
 * Weather Data Access Object.
 * <p>
 * Created by tamura_k on 2016/06/03.
 */
public class WeatherDao {

    private Realm realm;

    public WeatherDao(Realm realm) {
        this.realm = realm;
    }

    public WeatherEntity find(String cityId) {
        return realm.where(WeatherEntity.class)
                .equalTo("cityId", cityId)
                .findFirst();
    }

    public boolean exist(String cityId) {
        return (0 <= realm.where(WeatherEntity.class)
                .equalTo("cityId", cityId)
                .count());
    }

    public void insert(WeatherEntity weather) {
        realm.copyToRealm(weather);
    }

    public void delete(String cityId) {
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
