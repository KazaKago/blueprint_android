package com.kazakago.blueprint.domain.usecase.hierarchy.weather

import com.kazakago.blueprint.domain.model.global.state.State
import com.kazakago.blueprint.domain.model.hierarchy.city.CityId
import com.kazakago.blueprint.domain.usecase.output.weather.WeatherOutput
import kotlinx.coroutines.flow.Flow

interface SubscribeWeatherUseCase {

    operator fun invoke(cityId: CityId): Flow<State<WeatherOutput>>
}
