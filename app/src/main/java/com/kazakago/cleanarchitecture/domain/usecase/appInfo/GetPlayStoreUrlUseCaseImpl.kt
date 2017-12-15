package com.kazakago.cleanarchitecture.domain.usecase.appInfo

import com.kazakago.cleanarchitecture.domain.repository.appinfo.AppInfoRepository

class GetPlayStoreUrlUseCaseImpl(private val appInfoRepository: AppInfoRepository) : GetPlayStoreUrlUseCase {

    override fun execute(input: Unit): String = appInfoRepository.playStoreUrl

}