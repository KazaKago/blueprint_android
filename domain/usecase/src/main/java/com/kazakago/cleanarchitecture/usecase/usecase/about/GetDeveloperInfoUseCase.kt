package com.kazakago.cleanarchitecture.usecase.usecase.about

import com.kazakago.cleanarchitecture.model.about.DeveloperInfo

interface GetDeveloperInfoUseCase {

    operator fun invoke(): DeveloperInfo

}