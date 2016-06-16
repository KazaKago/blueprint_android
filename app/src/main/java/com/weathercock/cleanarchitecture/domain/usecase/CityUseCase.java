package com.weathercock.cleanarchitecture.domain.usecase;

import com.weathercock.cleanarchitecture.domain.model.city.CityModel;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * City UseCase
 *
 * @author Kensuke
 */
public interface CityUseCase {

    List<CityModel> findAll() throws IOException, JSONException;

}