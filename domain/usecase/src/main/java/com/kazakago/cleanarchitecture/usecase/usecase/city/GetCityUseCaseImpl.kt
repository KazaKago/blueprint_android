package com.kazakago.cleanarchitecture.usecase.usecase.city

import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.repository.city.CityRepository

internal class GetCityUseCaseImpl(private val cityRepository: CityRepository) : GetCityUseCase {

    override suspend fun invoke(): List<City> {
        return cityRepository.findAll()
    }

}
