package com.kazakago.blueprint.domain.usecase.hierarchy.about

import com.kazakago.blueprint.domain.repository.AboutRepository
import com.kazakago.blueprint.domain.usecase.output.about.AboutOutput

internal class GetAboutUseCaseImpl(private val aboutRepository: AboutRepository) : GetAboutUseCase {

    override suspend fun invoke(): AboutOutput {
        val appInfo = aboutRepository.getAppInfo()
        val developerInfo = aboutRepository.getDeveloperInfo()
        return AboutOutput(appInfo, developerInfo)
    }
}
