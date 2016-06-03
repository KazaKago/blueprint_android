package com.ignis.android_cleanarchitecture.data.repository;

import android.content.Context;

import com.ignis.android_cleanarchitecture.data.api.RetrofitManager;
import com.ignis.android_cleanarchitecture.data.api.WeatherApi;
import com.ignis.android_cleanarchitecture.data.dao.WeatherDao;
import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;
import com.ignis.android_cleanarchitecture.domain.repository.WeatherRepository;

import io.realm.Realm;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Weather Repository Implement
 * <p>
 * Created by tamura_k on 2016/05/27.
 */
public class WeatherRepositoryImpl implements WeatherRepository {

    private Context context;

    public WeatherRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override
    public Observable<WeatherModel> fetch(int cityId) {
        Retrofit retrofit = RetrofitManager.getInstance();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        return weatherApi.get(cityId);
    }

    @Override
    public WeatherModel find(Realm realm, int cityId) {
        WeatherDao weatherDao = new WeatherDao(realm);
        return weatherDao.find(cityId);
    }

    @Override
    public boolean exist(int cityId) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            WeatherDao weatherDao = new WeatherDao(realm);
            return weatherDao.isExist(cityId);
        } finally {
            if (realm != null) realm.close();
        }
    }

    @Override
    public void save(WeatherModel weatherModel) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            WeatherDao weatherDao = new WeatherDao(realm);
            weatherDao.save(weatherModel);
            realm.commitTransaction();
        } finally {
            if (realm != null) realm.close();
        }
    }

    @Override
    public void delete(int cityId) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            WeatherDao weatherDao = new WeatherDao(realm);
            weatherDao.delete(cityId);
            realm.commitTransaction();
        } finally {
            if (realm != null) realm.close();
        }
    }

}
