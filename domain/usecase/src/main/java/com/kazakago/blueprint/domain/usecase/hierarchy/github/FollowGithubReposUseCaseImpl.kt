package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.domain.model.github.GithubRepo
import com.kazakago.blueprint.domain.repository.GithubRepository
import com.kazakago.storeflowable.core.FlowableState

internal class FollowGithubReposUseCaseImpl(private val githubRepository: GithubRepository) : FollowGithubReposUseCase {

    override fun invoke(githubOrgName: GithubOrgName): FlowableState<List<GithubRepo>> {
        return githubRepository.followRepos(githubOrgName)
    }
}
