package com.kazakago.cleanarchitecture.domain.usecase.appInfo

import com.kazakago.cleanarchitecture.domain.repository.AppInfoRepository

/**
 * Created by tamura_k on 2017/04/27.
 */
class GetOfficialSiteUrlUseCaseImpl(private val appInfoRepository: AppInfoRepository) : GetOfficialSiteUrlUseCase {

    override fun execute(input: Unit): String = appInfoRepository.officialSiteUrl

}