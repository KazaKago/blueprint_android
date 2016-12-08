package com.kazakago.cleanarchitecture.data.repository;

import android.content.Context;

import com.kazakago.cleanarchitecture.data.dao.CityDao;
import com.kazakago.cleanarchitecture.data.mapper.CityMapper;
import com.kazakago.cleanarchitecture.domain.model.city.CityModel;
import com.kazakago.cleanarchitecture.domain.repository.CityRepository;

import java.io.IOException;

import io.reactivex.Observable;

/**
 * City Repository Implement
 * <p>
 * Created by tamura_k on 2016/05/27.
 */
public class CityRepositoryImpl implements CityRepository {

    private Context context;

    public CityRepositoryImpl(Context context) {
        this.context = context;
    }

    public Observable<CityModel> findAll() throws IOException {
        CityDao cityDao = new CityDao(context);
        return Observable.fromIterable(cityDao.find())
                .flatMap(prefEntity -> Observable.fromIterable(CityMapper.map(prefEntity)));
    }

}
