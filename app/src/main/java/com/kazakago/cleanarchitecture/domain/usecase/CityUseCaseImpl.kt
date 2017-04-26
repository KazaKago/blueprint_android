package com.kazakago.cleanarchitecture.domain.usecase

import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import com.kazakago.cleanarchitecture.domain.repository.CityRepository
import io.reactivex.Observable
import java.io.IOException

/**
 * City UseCase Implement
 *
 * @author Kensuke
 */
class CityUseCaseImpl(private val cityRepository: CityRepository) : CityUseCase {

    @Throws(IOException::class)
    override fun findAll(): Observable<CityModel> {
        return cityRepository.findAll()
    }

}
