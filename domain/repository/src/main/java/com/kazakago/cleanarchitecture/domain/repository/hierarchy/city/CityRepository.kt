package com.kazakago.cleanarchitecture.domain.repository.hierarchy.city

import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    fun subscribe(cityId: CityId): Flow<State<City>>

    fun subscribeAll(): Flow<State<List<City>>>

}
