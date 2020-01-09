package com.kazakago.cleanarchitecture.repository.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import com.kazakago.cleanarchitecture.repository.state.Observer
import com.kazakago.cleanarchitecture.repository.state.city.CityStoreDistributor
import kotlinx.coroutines.flow.Flow

internal class CityRepositoryImpl(context: Context) : CityRepository {

    private val cityStoreDistributor = CityStoreDistributor(context)

    override fun subscribe(cityId: CityId): Flow<StoreState<City>> {
        return Observer.subscribe(
            fetch = { cityStoreDistributor.get(cityId) }
        )
    }

    override fun subscribeAll(): Flow<StoreState<List<City>>> {
        return Observer.subscribe(
            fetch = { cityStoreDistributor.getAll() }
        )
    }

}
