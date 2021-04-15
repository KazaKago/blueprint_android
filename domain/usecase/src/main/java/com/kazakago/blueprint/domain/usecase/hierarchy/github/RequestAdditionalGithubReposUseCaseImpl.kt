package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import javax.inject.Inject

class RequestAdditionalGithubReposUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : RequestAdditionalGithubReposUseCase {

    override suspend fun invoke(githubOrgName: GithubOrgName, continueWhenError: Boolean) {
        return githubRepository.requestAdditionalRepos(githubOrgName, continueWhenError)
    }
}
