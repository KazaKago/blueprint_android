package com.weathercock.cleanarchitecture.domain.repository;

import com.weathercock.cleanarchitecture.domain.model.city.CityModel;

import java.util.List;

/**
 * City Repository
 *
 * @author Kensuke
 */
public interface CityRepository {

    List<CityModel> findAll();

}
