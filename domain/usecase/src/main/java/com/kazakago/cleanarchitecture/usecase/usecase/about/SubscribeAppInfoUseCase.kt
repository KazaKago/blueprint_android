package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.state.StoreState
import kotlinx.coroutines.flow.Flow

interface SubscribeAppInfoUseCase {

    operator fun invoke(): Flow<StoreState<AppInfo>>

}