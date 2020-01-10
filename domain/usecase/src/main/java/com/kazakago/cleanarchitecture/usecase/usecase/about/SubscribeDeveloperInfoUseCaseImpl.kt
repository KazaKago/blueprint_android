package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.repository.about.AboutRepository
import kotlinx.coroutines.flow.Flow

internal class SubscribeDeveloperInfoUseCaseImpl(private val aboutRepository: AboutRepository) : SubscribeDeveloperInfoUseCase {

    override fun invoke(): Flow<StoreState<DeveloperInfo>> {
        return aboutRepository.subscribeDeveloperInfo()
    }

}