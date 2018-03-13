package com.kazakago.cleanarchitecture.domain.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
import io.reactivex.Single

class GetCityUseCaseImpl(private val cityRepository: CityRepository) : GetCityUseCase {

    override fun execute(input: Unit): Single<List<City>> {
        return cityRepository.findAll()
    }

}
