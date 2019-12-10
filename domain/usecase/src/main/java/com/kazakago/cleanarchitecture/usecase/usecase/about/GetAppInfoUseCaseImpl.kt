package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.repository.about.AboutRepository

internal class GetAppInfoUseCaseImpl(private val aboutRepository: AboutRepository) : GetAppInfoUseCase {

    override fun invoke(): AppInfo {
        return aboutRepository.getAppInfo()
    }

}