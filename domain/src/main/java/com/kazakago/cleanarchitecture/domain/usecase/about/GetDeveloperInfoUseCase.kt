package com.kazakago.cleanarchitecture.domain.usecase.about

import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.usecase.UseCase
import io.reactivex.Single

interface GetDeveloperInfoUseCase : UseCase<Unit, Single<DeveloperInfo>>