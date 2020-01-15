package com.kazakago.cleanarchitecture.repository.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.State
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.dispatcher.CacheFlowDispatcher
import com.kazakago.cleanarchitecture.repository.distributor.weather.WeatherDistributor
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow

internal class WeatherRepositoryImpl(context: Context) : WeatherRepository {

    private val weatherStoreDistributor = WeatherDistributor(context)

    override fun subscribe(cityId: CityId): Flow<State<Weather>> {
        return CacheFlowDispatcher(
            loadState = { weatherStoreDistributor.subscribeState(cityId) },
            saveState = { weatherStoreDistributor.saveState(it, cityId) },
            loadContent = { weatherStoreDistributor.loadContent(cityId) },
            fetchContent = { weatherStoreDistributor.fetchContent(cityId) },
            saveContent = { weatherStoreDistributor.saveContent(it) },
            isStale = { it.isExpired() }
        ).subscribe()
    }

    override suspend fun request(cityId: CityId) {
        return CacheFlowDispatcher(
            loadState = { weatherStoreDistributor.subscribeState(cityId) },
            saveState = { weatherStoreDistributor.saveState(it, cityId) },
            loadContent = { weatherStoreDistributor.loadContent(cityId) },
            fetchContent = { weatherStoreDistributor.fetchContent(cityId) },
            saveContent = { weatherStoreDistributor.saveContent(it) },
            isStale = { true }
        ).request()
    }

}
