package com.kazakago.cleanarchitecture.domain.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import com.kazakago.cleanarchitecture.domain.repository.CityRepository
import io.reactivex.Observable

/**
 * City UseCase Implement
 *
 * @author Kensuke
 */
class GetCityUseCaseImpl(private val cityRepository: CityRepository) : GetCityUseCase {

    override fun execute(input: Unit): Observable<CityModel> {
        return cityRepository.findAll()
    }

}
