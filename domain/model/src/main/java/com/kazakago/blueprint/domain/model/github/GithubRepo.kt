package com.kazakago.blueprint.domain.model.github

import java.io.Serializable
import java.net.URL

data class GithubRepo(
    val id: GithubRepoId,
    val name: String,
    val url: URL,
) : Serializable
