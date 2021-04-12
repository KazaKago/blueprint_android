package com.kazakago.cleanarchitecture.domain.usecase.hierarchy.city

import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.city.CityRepository
import kotlinx.coroutines.flow.Flow

internal class SubscribeCityListUseCaseImpl(private val cityRepository: CityRepository) : SubscribeCityListUseCase {

    override fun invoke(): Flow<State<List<City>>> {
        return cityRepository.subscribeAll()
    }
}
