package com.kazakago.cleanarchitecture.domain.usecase

import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import io.reactivex.Observable
import java.io.IOException

/**
 * City UseCase
 *
 * @author Kensuke
 */
interface CityUseCase {

    @Throws(IOException::class)
    fun findAll(): Observable<CityModel>

}