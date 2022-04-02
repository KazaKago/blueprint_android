package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgAndRepos
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import com.kazakago.storeflowable.core.FlowLoadingState
import com.kazakago.storeflowable.core.combineState
import javax.inject.Inject

internal class FollowGithubReposUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : FollowGithubReposUseCase {

    override fun invoke(githubOrgName: GithubOrgName): FlowLoadingState<GithubOrgAndRepos> {
        return githubRepository.followOrg(githubOrgName)
            .combineState(githubRepository.followRepos(githubOrgName)) { githubOrg, githubRepos ->
                GithubOrgAndRepos(githubOrg, githubRepos)
            }
    }
}
