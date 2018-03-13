package com.kazakago.cleanarchitecture.domain.repository.appinfo

import com.kazakago.cleanarchitecture.domain.model.appinfo.AppInfo
import io.reactivex.Single

interface AppInfoRepository {

    fun getAppInfo(): Single<AppInfo>

}
