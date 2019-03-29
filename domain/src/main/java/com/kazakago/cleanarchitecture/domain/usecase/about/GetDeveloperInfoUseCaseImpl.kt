package com.kazakago.cleanarchitecture.domain.usecase.about

import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.repository.about.AboutRepository

internal class GetDeveloperInfoUseCaseImpl(private val aboutRepository: AboutRepository) : GetDeveloperInfoUseCase {

    override fun invoke(): DeveloperInfo {
        return aboutRepository.getDeveloperInfo()
    }

}