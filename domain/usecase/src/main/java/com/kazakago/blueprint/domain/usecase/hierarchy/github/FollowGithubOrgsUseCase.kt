package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.storeflowable.core.FlowableState

interface FollowGithubOrgsUseCase {

    operator fun invoke(): FlowableState<List<GithubOrg>>
}
