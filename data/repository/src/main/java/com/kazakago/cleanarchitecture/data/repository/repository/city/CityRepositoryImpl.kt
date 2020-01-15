package com.kazakago.cleanarchitecture.data.repository.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.dispatcher.FlowDispatcher
import com.kazakago.cleanarchitecture.data.repository.distributor.city.CityDistributor
import com.kazakago.cleanarchitecture.domain.model.city.City
import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.state.State
import com.kazakago.cleanarchitecture.domain.repository.city.CityRepository
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
