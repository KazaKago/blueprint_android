package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import com.kazakago.storeflowable.core.FlowLoadingState
import javax.inject.Inject

internal class FollowGithubReposUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : FollowGithubReposUseCase {

    override fun invoke(githubOrgName: GithubOrgName): FlowLoadingState<List<GithubRepo>> {
        return githubRepository.followRepos(githubOrgName)
    }
}
