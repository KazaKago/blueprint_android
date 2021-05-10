package com.kazakago.blueprint.domain.usecase.hierarchy.about

import com.kazakago.blueprint.domain.model.hierarchy.about.AboutInfo

interface GetAboutInfoUseCase {

    suspend operator fun invoke(): AboutInfo
}
