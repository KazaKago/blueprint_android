package com.kazakago.cleanarchitecture.domain.usecase.appinfo

import com.kazakago.cleanarchitecture.domain.model.appinfo.AppInfo
import com.kazakago.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Single

interface GetAppInfoUseCase : UseCase<Unit, Single<AppInfo>>