package com.weathercock.android_cleanarchitecture.domain.usecase;

import com.weathercock.android_cleanarchitecture.domain.model.city.CityModel;
import com.weathercock.android_cleanarchitecture.domain.repository.CityRepository;

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

    public List<CityModel> findAll() {
        return cityRepository.findAll();
    }

}
