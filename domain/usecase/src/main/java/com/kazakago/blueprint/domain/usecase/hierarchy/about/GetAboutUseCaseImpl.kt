package com.kazakago.blueprint.domain.usecase.hierarchy.about

import com.kazakago.blueprint.domain.repository.hierarchy.AboutRepository
import com.kazakago.blueprint.domain.usecase.output.about.AboutOutput
import javax.inject.Inject

class GetAboutUseCaseImpl @Inject constructor(
    private val aboutRepository: AboutRepository,
) : GetAboutUseCase {

    override suspend fun invoke(): AboutOutput {
        val appInfo = aboutRepository.getAppInfo()
        val developerInfo = aboutRepository.getDeveloperInfo()
        return AboutOutput(appInfo, developerInfo)
    }
}
