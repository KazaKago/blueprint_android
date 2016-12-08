package com.kazakago.cleanarchitecture.domain.repository;

import com.kazakago.cleanarchitecture.domain.model.city.CityModel;

import java.io.IOException;

import io.reactivex.Observable;

/**
 * City Repository
 *
 * @author Kensuke
 */
public interface CityRepository {

    Observable<CityModel> findAll() throws IOException;

}
