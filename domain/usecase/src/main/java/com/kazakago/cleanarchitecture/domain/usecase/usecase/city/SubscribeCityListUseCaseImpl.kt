package com.kazakago.cleanarchitecture.domain.usecase.usecase.city

import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.state.State
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow

internal class SubscribeCityListUseCaseImpl(private val cityRepository: CityRepository) : SubscribeCityListUseCase {

    override fun invoke(): Flow<State<List<City>>> {
        return cityRepository.subscribeAll()
    }

}
