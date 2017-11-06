package com.kazakago.cleanarchitecture.domain.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import com.kazakago.cleanarchitecture.domain.repository.CityRepository
import io.reactivex.Observable

class GetCityUseCaseImpl(private val cityRepository: CityRepository) : GetCityUseCase {

    override fun execute(input: Unit): Observable<CityModel> = cityRepository.findAll()

}
