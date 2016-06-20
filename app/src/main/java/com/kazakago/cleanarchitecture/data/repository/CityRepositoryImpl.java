package com.kazakago.cleanarchitecture.data.repository;

import android.content.Context;

import com.kazakago.cleanarchitecture.data.dao.CityDao;
import com.kazakago.cleanarchitecture.data.entity.city.CityEntity;
import com.kazakago.cleanarchitecture.data.entity.city.PrefEntity;
import com.kazakago.cleanarchitecture.domain.model.city.CityModel;
import com.kazakago.cleanarchitecture.domain.repository.CityRepository;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<CityModel> findAll() throws IOException, JSONException {
        List<CityModel> cityModelList = new ArrayList<>();

            CityDao cityDao = new CityDao(context);
            List<PrefEntity> prefEntityList = cityDao.find();
            for (PrefEntity prefEntity : prefEntityList) {
                String prefTitle = prefEntity.getTitle();
                for (CityEntity cityEntity : prefEntity.getCityList()) {
                    CityModel cityModel = new CityModel();
                    cityModel.setId(cityEntity.getId());
                    cityModel.setName(prefTitle + " " + cityEntity.getTitle());
                    cityModelList.add(cityModel);
                }
            }

        return cityModelList;
    }

}
