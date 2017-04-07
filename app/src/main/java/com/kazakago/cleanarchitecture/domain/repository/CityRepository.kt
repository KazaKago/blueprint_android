package com.kazakago.cleanarchitecture.domain.repository

import com.kazakago.cleanarchitecture.domain.model.city.CityModel
import io.reactivex.Observable
import java.io.IOException

/**
 * City Repository
 *
 * @author Kensuke
 */
interface CityRepository {

    @Throws(IOException::class)
    fun findAll(): Observable<CityModel>

}
