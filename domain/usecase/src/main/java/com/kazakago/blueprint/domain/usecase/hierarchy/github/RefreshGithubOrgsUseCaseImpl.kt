package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import javax.inject.Inject

internal class RefreshGithubOrgsUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : RefreshGithubOrgsUseCase {

    override suspend fun invoke() {
        return githubRepository.refreshOrgs()
    }
}
