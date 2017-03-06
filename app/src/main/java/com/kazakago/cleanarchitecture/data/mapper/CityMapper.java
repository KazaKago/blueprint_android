package com.kazakago.cleanarchitecture.data.mapper;

import android.support.annotation.Nullable;

import com.kazakago.cleanarchitecture.data.entity.city.PrefEntity;
import com.kazakago.cleanarchitecture.domain.model.city.CityModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tamura_k on 2016/12/08.
 */

public class CityMapper {

    public static List<CityModel> map(@Nullable PrefEntity entity) {
        if (entity != null && entity.getCityList() != null) {
            String prefTitle = entity.getTitle();
            return Observable.fromIterable(entity.getCityList())
                    .map(cityEntity -> {
                        CityModel cityModel = new CityModel();
                        cityModel.setId(cityEntity.getId());
                        cityModel.setName(prefTitle + " " + cityEntity.getTitle());
                        return cityModel;
                    })
                    .toList()
                    .blockingGet();
        } else {
            return null;
        }
    }

}
