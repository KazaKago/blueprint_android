package com.kazakago.cleanarchitecture.usecase.usecase.city

import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import kotlinx.coroutines.flow.Flow

internal class SubscribeCityUseCaseImpl(private val cityRepository: CityRepository) : SubscribeCityUseCase {

    override fun invoke(cityId: CityId): Flow<StoreState<City>> {
        return cityRepository.subscribe(cityId)
    }

}
