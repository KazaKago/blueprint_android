package com.kazakago.cleanarchitecture.domain.usecase.usecase.about

import com.kazakago.cleanarchitecture.domain.model.state.State
import com.kazakago.cleanarchitecture.domain.model.state.combineState
import com.kazakago.cleanarchitecture.domain.repository.about.AboutRepository
import com.kazakago.cleanarchitecture.domain.usecase.output.about.AboutOutput
import kotlinx.coroutines.flow.Flow

internal class SubscribeAboutUseCaseImpl(private val aboutRepository: AboutRepository) : SubscribeAboutUseCase {

    override fun invoke(): Flow<State<AboutOutput>> {
        val appInfoFlow = aboutRepository.subscribeAppInfo()
        val developerInfoFlow = aboutRepository.subscribeDeveloperInfo()
        return appInfoFlow.combineState(developerInfoFlow) { appInfo, developerInfo ->
            AboutOutput(appInfo, developerInfo)
        }
    }

}