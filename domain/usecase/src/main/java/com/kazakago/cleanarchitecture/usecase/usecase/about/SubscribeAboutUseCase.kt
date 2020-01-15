package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.state.State
import com.kazakago.cleanarchitecture.usecase.output.about.AboutOutput
import kotlinx.coroutines.flow.Flow

interface SubscribeAboutUseCase {

    operator fun invoke(): Flow<State<AboutOutput>>

}