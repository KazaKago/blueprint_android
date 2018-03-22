package com.kazakago.cleanarchitecture.domain.repository.about

import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo
import io.reactivex.Single

interface AboutRepository {

    fun getAppInfo(): Single<AppInfo>

    fun getDeveloperInfo(): Single<DeveloperInfo>

}
