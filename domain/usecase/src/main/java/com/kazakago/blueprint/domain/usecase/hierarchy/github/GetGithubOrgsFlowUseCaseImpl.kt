package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import com.kazakago.storeflowable.core.FlowLoadingState
import javax.inject.Inject

internal class GetGithubOrgsFlowUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : GetGithubOrgsFlowUseCase {

    override fun invoke(): FlowLoadingState<List<GithubOrg>> {
        return githubRepository.getOrgsFlow()
    }
}
