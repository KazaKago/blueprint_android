package com.kazakago.cleanarchitecture.domain.usecase.usecase.about

import com.kazakago.cleanarchitecture.domain.model.state.State
import com.kazakago.cleanarchitecture.domain.usecase.output.about.AboutOutput
import kotlinx.coroutines.flow.Flow

interface SubscribeAboutUseCase {

    operator fun invoke(): Flow<State<AboutOutput>>

}