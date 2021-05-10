package com.kazakago.blueprint.domain.usecase.hierarchy.about

import com.kazakago.blueprint.domain.model.hierarchy.about.AboutInfo
import com.kazakago.blueprint.domain.repository.hierarchy.AboutRepository
import javax.inject.Inject

internal class GetAboutInfoUseCaseImpl @Inject constructor(
    private val aboutRepository: AboutRepository,
) : GetAboutInfoUseCase {

    override suspend fun invoke(): AboutInfo {
        val appInfo = aboutRepository.getAppInfo()
        val developerInfo = aboutRepository.getDeveloperInfo()
        return AboutInfo(appInfo, developerInfo)
    }
}
