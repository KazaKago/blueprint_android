package com.kazakago.cleanarchitecture.repository.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import com.kazakago.cleanarchitecture.repository.state.city.CityStoreDistributor
import kotlinx.coroutines.flow.Flow

internal class CityRepositoryImpl(context: Context) : CityRepository {

    private val cityStoreDistributor = CityStoreDistributor(context)

    override fun subscribe(cityId: CityId): Flow<StoreState<City>> {
        return cityStoreDistributor.subscribe(cityId)
    }

    override fun subscribeAll(): Flow<StoreState<List<City>>> {
        return cityStoreDistributor.subscribeAll()
    }

}
