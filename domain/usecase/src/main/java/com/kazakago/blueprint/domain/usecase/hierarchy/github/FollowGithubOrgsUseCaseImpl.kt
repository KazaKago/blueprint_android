package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import com.kazakago.storeflowable.core.FlowLoadingState
import javax.inject.Inject

internal class FollowGithubOrgsUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : FollowGithubOrgsUseCase {

    override fun invoke(): FlowLoadingState<List<GithubOrg>> {
        return githubRepository.followOrgs()
    }
}
