package com.kazakago.cleanarchitecture.usecase.usecase.city

import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.state.State
import kotlinx.coroutines.flow.Flow

interface SubscribeCityListUseCase {

    operator fun invoke(): Flow<State<List<City>>>

}