package com.kazakago.cleanarchitecture.domain.usecase.about

import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo

interface GetDeveloperInfoUseCase {

    operator fun invoke(): DeveloperInfo

}