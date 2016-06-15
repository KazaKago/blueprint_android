package com.weathercock.cleanarchitecture.domain.usecase;

import com.weathercock.cleanarchitecture.domain.model.city.CityModel;

import java.util.List;

/**
 * City UseCase
 *
 * @author Kensuke
 */
public interface CityUseCase {

    List<CityModel> findAll();

}