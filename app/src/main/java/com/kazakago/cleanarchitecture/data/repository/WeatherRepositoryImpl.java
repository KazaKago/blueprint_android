package com.kazakago.cleanarchitecture.data.repository;

import com.kazakago.cleanarchitecture.data.api.WeatherApi;
import com.kazakago.cleanarchitecture.data.api.WeatherRetrofit;
import com.kazakago.cleanarchitecture.data.dao.WeatherDao;
import com.kazakago.cleanarchitecture.data.entity.weather.WeatherEntity;
import com.kazakago.cleanarchitecture.data.mapper.WeatherMapper;
import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel;
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository;

import io.reactivex.Single;
import io.realm.Realm;
import retrofit2.Retrofit;

/**
 * Weather Repository Implement
 * <p>
 * Created by tamura_k on 2016/05/27.
 */
public class WeatherRepositoryImpl implements WeatherRepository {

    public WeatherRepositoryImpl() {
    }

    @Override
    public Single<WeatherModel> fetch(String cityId) {
        Retrofit retrofit = WeatherRetrofit.getInstance();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        return weatherApi.get(cityId)
                .doOnSuccess(weatherEntity -> {
                    if (exist(cityId)) delete(cityId);
                    weatherEntity.setCityId(cityId);
                    insert(weatherEntity);
                })
                .map(WeatherMapper::map);
    }

    @Override
    public WeatherModel find(String cityId) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            WeatherDao weatherDao = new WeatherDao(realm);
            WeatherEntity weatherEntity = weatherDao.find(cityId);
            return WeatherMapper.map(weatherEntity);
        } finally {
            if (realm != null) realm.close();
        }
    }

    private boolean exist(String cityId) {
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

    private void delete(String cityId) {
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
