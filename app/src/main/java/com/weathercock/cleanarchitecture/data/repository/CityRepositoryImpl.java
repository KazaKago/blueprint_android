package com.weathercock.cleanarchitecture.data.repository;

import com.weathercock.cleanarchitecture.domain.model.city.CityModel;
import com.weathercock.cleanarchitecture.domain.repository.CityRepository;

import java.util.Arrays;
import java.util.List;

/**
 * City Repository Implement
 * <p>
 * Created by tamura_k on 2016/05/27.
 */
public class CityRepositoryImpl implements CityRepository {

    public CityRepositoryImpl() {
    }

    public List<CityModel> findAll() {
        CityModel cityModel1 = new CityModel();
        cityModel1.setId(130010);
        cityModel1.setName("東京");
        CityModel cityModel2 = new CityModel();
        cityModel2.setId(140010);
        cityModel2.setName("横浜");
        CityModel cityModel3 = new CityModel();
        cityModel3.setId(150010);
        cityModel3.setName("新潟");
        return Arrays.asList(cityModel1, cityModel2, cityModel3);
    }

}
