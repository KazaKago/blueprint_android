package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.repository.GithubRepository
import com.kazakago.storeflowable.core.FlowableState

internal class FollowGithubOrgsUseCaseImpl(private val githubRepository: GithubRepository) : FollowGithubOrgsUseCase {

    override fun invoke(): FlowableState<List<GithubOrg>> {
        return githubRepository.followOrgs()
    }
}
