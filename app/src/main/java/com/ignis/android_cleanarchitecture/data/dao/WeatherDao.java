package com.ignis.android_cleanarchitecture.data.dao;

import android.support.annotation.NonNull;

import com.ignis.android_cleanarchitecture.domain.model.ForecastModel;
import com.ignis.android_cleanarchitecture.domain.model.ImageModel;
import com.ignis.android_cleanarchitecture.domain.model.TemperatureUnitModel;
import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;

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

    public WeatherModel find(int cityId) {
        return getRealm().where(WeatherModel.class)
                .equalTo("cityId", cityId)
                .findFirst();
    }

    public boolean exist(int cityId) {
        return (0 <= getRealm().where(WeatherModel.class)
                .equalTo("cityId", cityId)
                .count());
    }

    public void insert(WeatherModel weatherModel) {
        getRealm().copyToRealm(weatherModel);
    }

    public void delete(int cityId) {
        WeatherModel weatherModel = find(cityId);
        if (weatherModel != null) {
            for (ForecastModel forecastModel : weatherModel.getForecasts()) {
                TemperatureUnitModel max = forecastModel.getTemperature().getMax();
                if (max != null) max.deleteFromRealm();
                TemperatureUnitModel min = forecastModel.getTemperature().getMin();
                if (min != null) min.deleteFromRealm();
                forecastModel.getTemperature().deleteFromRealm();
                forecastModel.getImage().deleteFromRealm();
            }
            weatherModel.getForecasts().deleteAllFromRealm();
            weatherModel.getCopyright().getProvider().deleteAllFromRealm();
            ImageModel image = weatherModel.getCopyright().getImage();
            if (image != null) image.deleteFromRealm();
            weatherModel.getCopyright().deleteFromRealm();
            weatherModel.getLocation().deleteFromRealm();
            weatherModel.getDescription().deleteFromRealm();
            weatherModel.getPinpointLocations().deleteAllFromRealm();
            weatherModel.deleteFromRealm();
        }
    }

}
