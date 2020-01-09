package com.kazakago.cleanarchitecture.repository.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreDistributor
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.state.weather.WeatherStoreDistributor
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.time.Duration

internal class WeatherRepositoryImpl(context: Context) : WeatherRepository {

    private val weatherStoreDistributor = WeatherStoreDistributor(context)

    override fun subscribe(cityId: CityId, expired: Duration): Flow<StoreState<Weather>> {
        return weatherStoreDistributor.subscribe(cityId)
            .onStart {
                CoroutineScope(Dispatchers.IO).launch { updateIfNeeded(cityId, expired) }
            }
    }

    private suspend fun updateIfNeeded(cityId: CityId, expired: Duration) {
        StoreDistributor(expired).execute(
            load = { weatherStoreDistributor.subscribe(cityId).first() },
            fetch = { weatherStoreDistributor.fetch(cityId) },
            save = { weatherStoreDistributor.save(cityId, it) }
        )
    }

}
