package com.kazakago.cleanarchitecture.data.repository;

import com.kazakago.cleanarchitecture.data.api.WeatherApi;
import com.kazakago.cleanarchitecture.data.api.WeatherRetrofit;
import com.kazakago.cleanarchitecture.data.dao.WeatherDao;
import com.kazakago.cleanarchitecture.data.entity.weather.WeatherEntity;
import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel;
import com.kazakago.cleanarchitecture.domain.repository.WeatherRepository;

import org.modelmapper.ModelMapper;

import io.realm.Realm;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Weather Repository Implement
 * <p>
 * Created by tamura_k on 2016/05/27.
 */
public class WeatherRepositoryImpl implements WeatherRepository {

    public WeatherRepositoryImpl() {
    }

    @Override
    public Observable<WeatherModel> fetch(String cityId) {
        Retrofit retrofit = WeatherRetrofit.getInstance();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        ModelMapper modelMapper = new ModelMapper();
        return weatherApi.get(cityId)
                .doOnNext(weatherEntity -> {
                    if (exist(cityId)) delete(cityId);
                    weatherEntity.setCityId(cityId);
                    insert(weatherEntity);
                })
                .map(weatherEntity -> modelMapper.map(weatherEntity, WeatherModel.class));
    }

    @Override
    public WeatherModel find(String cityId) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            WeatherDao weatherDao = new WeatherDao(realm);
            WeatherEntity weatherEntity = weatherDao.find(cityId);

            if (weatherEntity != null) {
                ModelMapper modelMapper = new ModelMapper();
                return modelMapper.map(weatherEntity, WeatherModel.class);
            } else {
                return null;
            }
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
