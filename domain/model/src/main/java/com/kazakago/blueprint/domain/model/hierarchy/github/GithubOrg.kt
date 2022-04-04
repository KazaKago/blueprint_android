package com.kazakago.blueprint.domain.model.hierarchy.github

import java.io.Serializable
import java.net.URL

data class GithubOrg(
    val id: GithubOrgId,
    val name: GithubOrgName,
    val imageUrl: URL,
) : Serializable
