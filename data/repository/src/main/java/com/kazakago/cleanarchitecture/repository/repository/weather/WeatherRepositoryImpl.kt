package com.kazakago.cleanarchitecture.repository.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.dispatcher.CacheFlowDispatcher
import com.kazakago.cleanarchitecture.repository.distributor.weather.WeatherDistributor
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import java.time.Duration

internal class WeatherRepositoryImpl(context: Context) : WeatherRepository {

    private val weatherStoreDistributor = WeatherDistributor(context)

    override fun subscribe(cityId: CityId, expired: Duration): Flow<StoreState<Weather>> {
        return CacheFlowDispatcher(expired).subscribe(
            load = { weatherStoreDistributor.subscribe(cityId) },
            fetch = { weatherStoreDistributor.fetch(cityId) },
            save = { weatherStoreDistributor.save(cityId, it) }
        )
    }

}
