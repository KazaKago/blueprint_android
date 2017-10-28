package com.kazakago.cleanarchitecture.domain.usecase.appInfo

import com.kazakago.cleanarchitecture.domain.repository.AppInfoRepository

class GetOfficialSiteUrlUseCaseImpl(private val appInfoRepository: AppInfoRepository) : GetOfficialSiteUrlUseCase {

    override fun execute(input: Unit): String = appInfoRepository.officialSiteUrl

}