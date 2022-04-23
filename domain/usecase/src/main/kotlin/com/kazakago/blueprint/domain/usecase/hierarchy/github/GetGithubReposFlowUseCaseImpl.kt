package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgAndRepos
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import com.kazakago.storeflowable.core.FlowLoadingState
import com.kazakago.storeflowable.core.combineState
import javax.inject.Inject

internal class GetGithubReposFlowUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : GetGithubReposFlowUseCase {

    override fun invoke(githubOrgName: GithubOrgName): FlowLoadingState<GithubOrgAndRepos> {
        return githubRepository.getOrgFlow(githubOrgName)
            .combineState(githubRepository.getReposFlow(githubOrgName)) { githubOrg, githubRepos ->
                GithubOrgAndRepos(githubOrg, githubRepos)
            }
    }
}
