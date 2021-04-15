package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import com.kazakago.storeflowable.core.FlowableState
import javax.inject.Inject

class FollowGithubOrgsUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : FollowGithubOrgsUseCase {

    override fun invoke(): FlowableState<List<GithubOrg>> {
        return githubRepository.followOrgs()
    }
}
