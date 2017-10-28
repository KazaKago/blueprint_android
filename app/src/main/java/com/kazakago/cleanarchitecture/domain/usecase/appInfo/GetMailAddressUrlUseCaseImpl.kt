package com.kazakago.cleanarchitecture.domain.usecase.appInfo

import com.kazakago.cleanarchitecture.domain.repository.AppInfoRepository

class GetMailAddressUrlUseCaseImpl(private val appInfoRepository: AppInfoRepository) : GetMailAddressUrlUseCase {

    override fun execute(input: Unit): String = appInfoRepository.mailAddressUrl

}