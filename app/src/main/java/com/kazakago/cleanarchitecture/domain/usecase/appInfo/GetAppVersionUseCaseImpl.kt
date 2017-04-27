package com.kazakago.cleanarchitecture.domain.usecase.appInfo

import com.kazakago.cleanarchitecture.domain.repository.AppInfoRepository

/**
 * Created by tamura_k on 2017/04/27.
 */
class GetAppVersionUseCaseImpl(private val appInfoRepository: AppInfoRepository) : GetAppVersionUseCase {

    override fun execute(input: Unit): String {
        return appInfoRepository.appVersion
    }

}