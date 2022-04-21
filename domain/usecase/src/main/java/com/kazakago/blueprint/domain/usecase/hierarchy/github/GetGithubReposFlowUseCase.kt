package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgAndRepos
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.storeflowable.core.FlowLoadingState

interface GetGithubReposFlowUseCase {

    operator fun invoke(githubOrgName: GithubOrgName): FlowLoadingState<GithubOrgAndRepos>
}
