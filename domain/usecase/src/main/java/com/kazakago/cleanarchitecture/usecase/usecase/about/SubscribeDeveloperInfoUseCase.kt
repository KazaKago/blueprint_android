package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.model.state.StoreState
import kotlinx.coroutines.flow.Flow

interface SubscribeDeveloperInfoUseCase {

    operator fun invoke(): Flow<StoreState<DeveloperInfo>>

}