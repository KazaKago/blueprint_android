package com.kazakago.cleanarchitecture.repository.repository.city

import android.content.Context
import com.kazakago.cleanarchitecture.memory.memory.city.CityMemory
import com.kazakago.cleanarchitecture.model.city.City
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreDistributor
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.repository.city.CityRepository
import com.kazakago.cleanarchitecture.repository.mapper.city.CityEntityMapper
import com.kazakago.cleanarchitecture.repository.mapper.city.CityStateMapper
import com.kazakago.cleanarchitecture.resource.dao.city.CityDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Duration

internal class CityRepositoryImpl(context: Context) : CityRepository {

    private val cityDao = CityDao(context)
    private val cityMapper = CityEntityMapper()
    private val cityStateMapper = CityStateMapper()

    override fun subscribe(cityId: CityId): Flow<StoreState<City>> {
//        return load()
//            .onStart {
//                CoroutineScope(Dispatchers.IO).launch { distribute(cityId) }
//            }
    }

    override fun subscribeAll(): Flow<StoreState<List<City>>> {
        return load()
            .onStart {
                CoroutineScope(Dispatchers.IO).launch { distribute() }
            }
    }

    private suspend fun distribute() {
        StoreDistributor(Duration.ZERO).execute(
            load = { load().first() },
            fetch = { fetch() },
            save = { save(it) }
        )
    }

    private fun load(): Flow<StoreState<List<City>>> {
        return CityMemory.cityListState.asFlow()
            .map {
                cityStateMapper.map(it)
            }
    }

    private suspend fun fetch(): List<City> {
        val prefEntityList = withContext(Dispatchers.IO) { cityDao.find() }
        return prefEntityList.flatMap { cityMapper.map(it) }
    }

    private suspend fun save(cityList: StoreState<List<City>>) {
        val memoryState = cityStateMapper.reverse(cityList)
        CityMemory.cityListState.send(memoryState)
    }

}
