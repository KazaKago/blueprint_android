package com.kazakago.cleanarchitecture.domain.usecase.about

import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.repository.about.AboutRepository

internal class GetAppInfoUseCaseImpl(private val aboutRepository: AboutRepository) : GetAppInfoUseCase {

    override fun invoke(input: Unit): AppInfo {
        return aboutRepository.getAppInfo()
    }

}