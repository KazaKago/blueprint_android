package com.kazakago.blueprint.domain.model.github

import java.io.Serializable

data class GithubOrgAndRepos(
    val githubOrg: GithubOrg,
    val githubRepos: List<GithubRepo>,
) : Serializable
