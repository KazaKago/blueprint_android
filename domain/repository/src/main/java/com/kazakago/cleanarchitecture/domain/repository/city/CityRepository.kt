package com.kazakago.cleanarchitecture.domain.repository.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.state.State
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    fun subscribe(cityId: CityId): Flow<State<City>>

    fun subscribeAll(): Flow<State<List<City>>>

}
