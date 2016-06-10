package com.ignis.android_cleanarchitecture.data.repository;

import android.content.Context;

import com.ignis.android_cleanarchitecture.data.api.WeatherApi;
import com.ignis.android_cleanarchitecture.data.api.WeatherRetrofit;
import com.ignis.android_cleanarchitecture.data.dao.WeatherDao;
import com.ignis.android_cleanarchitecture.data.entity.WeatherEntity;
import com.ignis.android_cleanarchitecture.data.entity.mapper.WeatherMapper;
import com.ignis.android_cleanarchitecture.domain.model.weather.WeatherModel;
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
        Retrofit retrofit = WeatherRetrofit.getInstance();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        return weatherApi.get(cityId)
                .doOnNext(weatherEntity -> {
                    if (exist(cityId)) delete(cityId);
                    weatherEntity.setCityId(cityId);
                    insert(weatherEntity);
                })
                .map(WeatherMapper::convert);
    }

    @Override
    public WeatherModel find(int cityId) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            WeatherDao weatherDao = new WeatherDao(realm);
            WeatherEntity weatherEntity = weatherDao.find(cityId);
            return WeatherMapper.convert(weatherEntity);
        } finally {
            if (realm != null) realm.close();
        }
    }

    private boolean exist(int cityId) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            WeatherDao weatherDao = new WeatherDao(realm);
            return weatherDao.exist(cityId);
        } finally {
            if (realm != null) realm.close();
        }
    }

    private void insert(WeatherEntity weatherEntity) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            WeatherDao weatherDao = new WeatherDao(realm);
            weatherDao.insert(weatherEntity);
            realm.commitTransaction();
        } finally {
            if (realm != null) realm.close();
        }
    }

    private void delete(int cityId) {
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
