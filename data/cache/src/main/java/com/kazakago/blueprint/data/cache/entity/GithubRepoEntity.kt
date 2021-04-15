package com.kazakago.blueprint.data.cache.entity

import java.net.URL

data class GithubRepoEntity(
    val id: Long,
    val fullName: String,
    val htmlUrl: URL,
)
