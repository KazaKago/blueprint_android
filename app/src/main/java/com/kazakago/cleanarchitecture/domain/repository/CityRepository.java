package com.kazakago.cleanarchitecture.domain.repository;

import com.kazakago.cleanarchitecture.domain.model.city.CityModel;

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
