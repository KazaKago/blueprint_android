package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName

interface RequestAdditionalGithubReposUseCase {

    suspend operator fun invoke(githubOrgName: GithubOrgName, continueWhenError: Boolean)
}
