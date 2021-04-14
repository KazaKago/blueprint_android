package com.kazakago.blueprint.domain.usecase.hierarchy.github

interface RequestAdditionalGithubOrgsUseCase {

    suspend operator fun invoke(continueWhenError: Boolean)
}
