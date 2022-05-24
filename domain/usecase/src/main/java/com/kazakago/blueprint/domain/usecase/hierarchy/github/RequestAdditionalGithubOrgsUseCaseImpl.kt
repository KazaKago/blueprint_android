package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import javax.inject.Inject

internal class RequestAdditionalGithubOrgsUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : RequestAdditionalGithubOrgsUseCase {

    override suspend fun invoke(continueWhenError: Boolean) {
        return githubRepository.requestAdditionalOrgs(continueWhenError = continueWhenError)
    }
}
