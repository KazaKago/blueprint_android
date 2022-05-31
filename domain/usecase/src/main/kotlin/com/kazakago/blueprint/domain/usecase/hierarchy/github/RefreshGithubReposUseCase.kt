package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName

interface RefreshGithubReposUseCase {

    suspend operator fun invoke(githubOrgName: GithubOrgName)
}
