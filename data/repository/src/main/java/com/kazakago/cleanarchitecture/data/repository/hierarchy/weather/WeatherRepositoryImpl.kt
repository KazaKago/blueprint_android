package com.kazakago.cleanarchitecture.data.repository.hierarchy.weather

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.global.dispatcher.CacheFlowDispatcher
import com.kazakago.cleanarchitecture.data.repository.distributor.weather.WeatherDistributor
import com.kazakago.cleanarchitecture.domain.model.hierarchy.city.CityId
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.model.hierarchy.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow

internal class WeatherRepositoryImpl(context: Context) : WeatherRepository {

    private val weatherStoreDistributor = WeatherDistributor(context)

    override fun subscribe(cityId: CityId): Flow<State<Weather>> {
        return CacheFlowDispatcher(
            loadState = { weatherStoreDistributor.subscribeState(cityId) },
            saveState = { weatherStoreDistributor.saveState(it, cityId) },
            loadContent = { weatherStoreDistributor.loadContent(cityId) },
            fetchContent = { weatherStoreDistributor.fetchContent(cityId) },
            saveContent = { weatherStoreDistributor.saveContent(it) }
        ).subscribe(isStale = { it.isExpired() })
    }

    override suspend fun request(cityId: CityId) {
        return CacheFlowDispatcher(
            loadState = { weatherStoreDistributor.subscribeState(cityId) },
            saveState = { weatherStoreDistributor.saveState(it, cityId) },
            loadContent = { weatherStoreDistributor.loadContent(cityId) },
            fetchContent = { weatherStoreDistributor.fetchContent(cityId) },
            saveContent = { weatherStoreDistributor.saveContent(it) }
        ).request()
    }

}
