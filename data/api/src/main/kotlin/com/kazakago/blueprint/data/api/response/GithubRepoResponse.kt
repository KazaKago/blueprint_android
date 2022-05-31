package com.kazakago.blueprint.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepoResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("html_url")
    val htmlUrl: String
)
