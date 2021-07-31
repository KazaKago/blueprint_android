package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.storeflowable.core.FlowLoadingState

interface FollowGithubOrgsUseCase {

    operator fun invoke(): FlowLoadingState<List<GithubOrg>>
}
