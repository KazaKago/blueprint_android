package com.kazakago.cleanarchitecture.domain.usecase.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.state.State
import kotlinx.coroutines.flow.Flow

interface SubscribeCityListUseCase {

    operator fun invoke(): Flow<State<List<City>>>

}