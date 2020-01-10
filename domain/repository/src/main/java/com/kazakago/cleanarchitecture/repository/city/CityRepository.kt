package com.kazakago.cleanarchitecture.repository.city

import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import kotlinx.coroutines.flow.Flow

interface CityRepository {

    fun subscribe(cityId: CityId): Flow<StoreState<City>>

    fun subscribeAll(): Flow<StoreState<List<City>>>

}
