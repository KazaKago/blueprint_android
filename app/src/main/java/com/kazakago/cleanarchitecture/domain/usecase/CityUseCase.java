package com.kazakago.cleanarchitecture.domain.usecase;

import com.kazakago.cleanarchitecture.domain.model.city.CityModel;

import java.io.IOException;

import io.reactivex.Observable;

/**
 * City UseCase
 *
 * @author Kensuke
 */
public interface CityUseCase {

    Observable<CityModel> findAll() throws IOException;

}