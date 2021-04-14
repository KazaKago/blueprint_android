package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.domain.repository.GithubRepository

internal class RefreshGithubReposUseCaseImpl(private val githubRepository: GithubRepository) : RefreshGithubReposUseCase {

    override suspend fun invoke(githubOrgName: GithubOrgName) {
        return githubRepository.refreshRepos(githubOrgName)
    }
}
