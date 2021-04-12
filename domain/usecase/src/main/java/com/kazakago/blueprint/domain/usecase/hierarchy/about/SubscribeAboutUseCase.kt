package com.kazakago.blueprint.domain.usecase.hierarchy.about

import com.kazakago.blueprint.domain.model.global.state.State
import com.kazakago.blueprint.domain.usecase.output.about.AboutOutput
import kotlinx.coroutines.flow.Flow

interface SubscribeAboutUseCase {

    operator fun invoke(): Flow<State<AboutOutput>>
}
