package com.kazakago.cleanarchitecture.domain.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository

internal class GetCityUseCaseImpl(private val cityRepository: CityRepository) : GetCityUseCase {

    override suspend fun invoke(): List<City> {
        return cityRepository.findAll()
    }

}
