package com.kazakago.cleanarchitecture.usecase.usecase.city

import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.state.StoreState
import kotlinx.coroutines.flow.Flow

interface SubscribeCityListUseCase {

    operator fun invoke(): Flow<StoreState<List<City>>>

}