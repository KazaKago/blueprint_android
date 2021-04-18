package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import javax.inject.Inject

internal class RefreshGithubReposUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : RefreshGithubReposUseCase {

    override suspend fun invoke(githubOrgName: GithubOrgName) {
        return githubRepository.refreshRepos(githubOrgName)
    }
}
