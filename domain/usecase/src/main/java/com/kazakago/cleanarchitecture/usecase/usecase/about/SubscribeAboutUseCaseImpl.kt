package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.model.state.zip
import com.kazakago.cleanarchitecture.repository.about.AboutRepository
import com.kazakago.cleanarchitecture.usecase.output.about.AboutOutput
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal class SubscribeAboutUseCaseImpl(private val aboutRepository: AboutRepository) : SubscribeAboutUseCase {

    override fun invoke(): Flow<StoreState<AboutOutput>> {
        val appInfoFlow = aboutRepository.subscribeAppInfo()
        val developerInfoFlow = aboutRepository.subscribeDeveloperInfo()
        return appInfoFlow.combine(developerInfoFlow) { appInfoState, developerInfoState ->
            appInfoState.zip(developerInfoState) { appInfo, developerInfo ->
                AboutOutput(appInfo, developerInfo)
            }
        }
    }

}