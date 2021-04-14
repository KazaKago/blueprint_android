package com.kazakago.blueprint.domain.model.github

import java.io.Serializable

data class GithubOrg(
    val id: GithubOrgId,
    val name: GithubOrgName,
) : Serializable
