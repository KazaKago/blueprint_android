package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.state.StoreState
import com.kazakago.cleanarchitecture.repository.about.AboutRepository
import kotlinx.coroutines.flow.Flow

internal class SubscribeAppInfoUseCaseImpl(private val aboutRepository: AboutRepository) : SubscribeAppInfoUseCase {

    override fun invoke(): Flow<StoreState<AppInfo>> {
        return aboutRepository.subscribeAppInfo()
    }

}