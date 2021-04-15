package com.kazakago.blueprint.domain.model.hierarchy.github

import java.io.Serializable

data class GithubOrg(
    val id: GithubOrgId,
    val name: GithubOrgName,
) : Serializable
