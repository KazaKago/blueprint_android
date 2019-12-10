package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.about.AppInfo

interface GetAppInfoUseCase {

    operator fun invoke(): AppInfo

}