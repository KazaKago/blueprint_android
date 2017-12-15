package com.kazakago.cleanarchitecture.domain.usecase.appInfo

import com.kazakago.cleanarchitecture.domain.repository.appinfo.AppInfoRepository

class GetMailAddressUrlUseCaseImpl(private val appInfoRepository: AppInfoRepository) : GetMailAddressUrlUseCase {

    override fun execute(input: Unit): String = appInfoRepository.mailAddressUrl

}