package com.kazakago.cleanarchitecture.data.repository.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.dispatcher.CacheFlowDispatcher
import com.kazakago.cleanarchitecture.data.repository.distributor.weather.WeatherDistributor
import com.kazakago.cleanarchitecture.domain.model.city.CityId
import com.kazakago.cleanarchitecture.domain.model.state.State
import com.kazakago.cleanarchitecture.domain.model.weather.Weather
import com.kazakago.cleanarchitecture.domain.repository.weather.WeatherRepository
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
