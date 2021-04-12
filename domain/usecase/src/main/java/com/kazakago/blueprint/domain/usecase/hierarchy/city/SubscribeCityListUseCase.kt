package com.kazakago.blueprint.domain.usecase.hierarchy.city

import com.kazakago.blueprint.domain.model.global.state.State
import com.kazakago.blueprint.domain.model.hierarchy.city.City
import kotlinx.coroutines.flow.Flow

interface SubscribeCityListUseCase {

    operator fun invoke(): Flow<State<List<City>>>
}
