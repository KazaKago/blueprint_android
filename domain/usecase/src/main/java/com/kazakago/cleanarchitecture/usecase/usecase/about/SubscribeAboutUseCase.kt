package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.usecase.output.about.AboutOutput
import kotlinx.coroutines.flow.Flow

interface SubscribeAboutUseCase {

    operator fun invoke(): Flow<StoreState<AboutOutput>>

}