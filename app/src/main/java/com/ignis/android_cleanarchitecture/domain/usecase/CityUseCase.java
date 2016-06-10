package com.ignis.android_cleanarchitecture.domain.usecase;

import com.ignis.android_cleanarchitecture.domain.model.city.CityModel;

import java.util.List;

/**
 * City UseCase
 *
 * @author Kensuke
 */
public interface CityUseCase {

    List<CityModel> findAll();

}