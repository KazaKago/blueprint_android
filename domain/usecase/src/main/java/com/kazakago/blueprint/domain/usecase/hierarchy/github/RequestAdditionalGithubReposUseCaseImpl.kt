package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.domain.repository.GithubRepository

internal class RequestAdditionalGithubReposUseCaseImpl(private val githubRepository: GithubRepository) : RequestAdditionalGithubReposUseCase {

    override suspend fun invoke(githubOrgName: GithubOrgName, continueWhenError: Boolean) {
        return githubRepository.requestAdditionalRepos(githubOrgName, continueWhenError)
    }
}
