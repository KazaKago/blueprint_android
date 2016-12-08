package com.kazakago.cleanarchitecture.domain.usecase;

import com.kazakago.cleanarchitecture.domain.model.city.CityModel;
import com.kazakago.cleanarchitecture.domain.repository.CityRepository;

import java.io.IOException;

import io.reactivex.Observable;

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

    public Observable<CityModel> findAll() throws IOException {
        return cityRepository.findAll();
    }

}
