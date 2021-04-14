package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.repository.GithubRepository

internal class RefreshGithubOrgsUseCaseImpl(private val githubRepository: GithubRepository) : RefreshGithubOrgsUseCase {

    override suspend fun invoke() {
        return githubRepository.refreshOrgs()
    }
}
