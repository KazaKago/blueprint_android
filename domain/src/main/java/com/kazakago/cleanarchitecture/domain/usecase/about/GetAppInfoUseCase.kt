package com.kazakago.cleanarchitecture.domain.usecase.about

import com.kazakago.cleanarchitecture.domain.model.about.AppInfo

interface GetAppInfoUseCase {

    operator fun invoke(): AppInfo

}