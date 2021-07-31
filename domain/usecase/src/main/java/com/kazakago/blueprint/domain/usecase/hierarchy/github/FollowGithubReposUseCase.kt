package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.storeflowable.core.FlowLoadingState

interface FollowGithubReposUseCase {

    operator fun invoke(githubOrgName: GithubOrgName): FlowLoadingState<List<GithubRepo>>
}
