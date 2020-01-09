package com.kazakago.cleanarchitecture.repository.repository.weather

import android.content.Context
import com.kazakago.cleanarchitecture.model.city.CityId
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.weather.Weather
import com.kazakago.cleanarchitecture.repository.state.CacheObserver
import com.kazakago.cleanarchitecture.repository.state.weather.WeatherStoreDistributor
import com.kazakago.cleanarchitecture.repository.weather.WeatherRepository
import kotlinx.coroutines.flow.Flow
import java.time.Duration

internal class WeatherRepositoryImpl(context: Context) : WeatherRepository {

    private val weatherStoreDistributor = WeatherStoreDistributor(context)

    override fun subscribe(cityId: CityId, expired: Duration): Flow<StoreState<Weather>> {
        return CacheObserver(expired).subscribe(
            load = { weatherStoreDistributor.subscribe(cityId) },
            fetch = { weatherStoreDistributor.fetch(cityId) },
            save = { weatherStoreDistributor.save(cityId, it) }
        )
    }

}
