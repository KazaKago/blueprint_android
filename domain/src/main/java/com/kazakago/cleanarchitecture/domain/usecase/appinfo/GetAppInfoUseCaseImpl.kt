package com.kazakago.cleanarchitecture.domain.usecase.appinfo

import com.kazakago.cleanarchitecture.domain.model.appinfo.AppInfo
import com.kazakago.cleanarchitecture.domain.repository.appinfo.AppInfoRepository
import io.reactivex.Single

class GetAppInfoUseCaseImpl(private val appInfoRepository: AppInfoRepository) : GetAppInfoUseCase {

    override fun execute(input: Unit): Single<AppInfo> {
        return appInfoRepository.getAppInfo()
    }

}