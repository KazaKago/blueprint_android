package com.kazakago.cleanarchitecture.domain.usecase.about

import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.usecase.UseCase

interface GetAppInfoUseCase : UseCase<Unit, AppInfo>