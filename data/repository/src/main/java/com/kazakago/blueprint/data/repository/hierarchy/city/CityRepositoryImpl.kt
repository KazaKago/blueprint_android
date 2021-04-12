package com.kazakago.blueprint.data.repository.hierarchy.city

import android.content.Context
import com.kazakago.blueprint.data.repository.global.dispatcher.FlowDispatcher
import com.kazakago.blueprint.data.repository.mapper.city.CityEntityMapper
import com.kazakago.blueprint.data.resource.hierarchy.city.CityDao
import com.kazakago.blueprint.domain.model.global.state.State
import com.kazakago.blueprint.domain.model.global.state.mapContent
import com.kazakago.blueprint.domain.model.hierarchy.city.City
import com.kazakago.blueprint.domain.model.hierarchy.city.CityId
import com.kazakago.blueprint.domain.repository.hierarchy.city.CityRepository
import kotlinx.coroutines.flow.Flow

internal class CityRepositoryImpl(context: Context) : CityRepository {

    private val cityDao = CityDao(context)
    private val cityMapper = CityEntityMapper()

    override fun subscribe(cityId: CityId): Flow<State<City>> {
        return subscribeAll()
            .mapContent { cityList ->
                cityList.first { it.id == cityId }
            }
    }

    override fun subscribeAll(): Flow<State<List<City>>> {
        return FlowDispatcher(fetchOrigin = { cityDao.getAll() })
            .subscribe()
            .mapContent { prefEntities ->
                prefEntities.flatMap { cityMapper.map(it) }
            }
    }
}
