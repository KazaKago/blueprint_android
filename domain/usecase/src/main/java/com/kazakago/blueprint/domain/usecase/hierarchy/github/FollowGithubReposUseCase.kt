package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.github.GithubOrgName
import com.kazakago.blueprint.domain.model.github.GithubRepo
import com.kazakago.storeflowable.core.FlowableState

interface FollowGithubReposUseCase {

    operator fun invoke(githubOrgName: GithubOrgName): FlowableState<List<GithubRepo>>
}
