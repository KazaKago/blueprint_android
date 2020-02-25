package com.kazakago.cleanarchitecture.data.repository.hierarchy.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.global.dispatcher.FlowDispatcher
import com.kazakago.cleanarchitecture.data.repository.distributor.city.CityDistributor
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.City
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.city.CityRepository
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
