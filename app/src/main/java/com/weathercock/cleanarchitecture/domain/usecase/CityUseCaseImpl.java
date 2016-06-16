package com.weathercock.cleanarchitecture.domain.usecase;

import com.weathercock.cleanarchitecture.domain.model.city.CityModel;
import com.weathercock.cleanarchitecture.domain.repository.CityRepository;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * City UseCase Implement
 *
 * @author Kensuke
 */
public class CityUseCaseImpl implements CityUseCase {

    private CityRepository cityRepository;

    public CityUseCaseImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<CityModel> findAll() throws IOException, JSONException {
        return cityRepository.findAll();
    }

}
