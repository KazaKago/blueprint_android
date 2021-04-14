package com.kazakago.blueprint.domain.usecase.hierarchy.about

import com.kazakago.blueprint.domain.usecase.output.about.AboutOutput

interface GetAboutUseCase {

    suspend operator fun invoke(): AboutOutput
}
