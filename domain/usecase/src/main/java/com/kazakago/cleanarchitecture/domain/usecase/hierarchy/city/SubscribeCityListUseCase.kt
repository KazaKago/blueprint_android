package com.kazakago.cleanarchitecture.domain.usecase.hierarchy.city

import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import kotlinx.coroutines.flow.Flow

interface SubscribeCityListUseCase {

    operator fun invoke(): Flow<State<List<City>>>
}
