package com.kazakago.cleanarchitecture.domain.usecase.hierarchy.about

import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.usecase.output.about.AboutOutput
import kotlinx.coroutines.flow.Flow

interface SubscribeAboutUseCase {

    operator fun invoke(): Flow<State<AboutOutput>>

}