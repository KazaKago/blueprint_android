package com.kazakago.cleanarchitecture.repository.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.State
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import com.kazakago.cleanarchitecture.repository.dispatcher.FlowDispatcher
import com.kazakago.cleanarchitecture.repository.distributor.city.CityDistributor
import kotlinx.coroutines.flow.Flow

internal class CityRepositoryImpl(context: Context) : CityRepository {

    private val cityStoreDistributor = CityDistributor(context)

    override fun subscribe(cityId: CityId): Flow<State<City>> {
        return FlowDispatcher(
            fetch = { cityStoreDistributor.get(cityId) }
        ).subscribe()
    }

    override fun subscribeAll(): Flow<State<List<City>>> {
        return FlowDispatcher(
            fetch = { cityStoreDistributor.getAll() }
        ).subscribe()
    }

}
