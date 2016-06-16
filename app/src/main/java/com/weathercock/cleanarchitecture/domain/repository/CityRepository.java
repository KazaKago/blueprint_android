package com.weathercock.cleanarchitecture.domain.repository;

import com.weathercock.cleanarchitecture.domain.model.city.CityModel;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * City Repository
 *
 * @author Kensuke
 */
public interface CityRepository {

    List<CityModel> findAll() throws IOException, JSONException;

}
