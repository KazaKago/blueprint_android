package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.storeflowable.core.FlowLoadingState

interface GetGithubOrgsFlowUseCase {

    operator fun invoke(): FlowLoadingState<List<GithubOrg>>
}
