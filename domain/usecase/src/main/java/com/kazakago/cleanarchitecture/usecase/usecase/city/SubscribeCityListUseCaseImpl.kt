package com.kazakago.cleanarchitecture.usecase.usecase.city

import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow

internal class SubscribeCityListUseCaseImpl(private val cityRepository: CityRepository) : SubscribeCityListUseCase {

    override fun invoke(): Flow<StoreState<List<City>>> {
        return cityRepository.subscribeAll()
    }

}
