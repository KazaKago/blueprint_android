package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.repository.about.AboutRepository

internal class GetDeveloperInfoUseCaseImpl(private val aboutRepository: AboutRepository) : GetDeveloperInfoUseCase {

    override fun invoke(): DeveloperInfo {
        return aboutRepository.getDeveloperInfo()
    }

}