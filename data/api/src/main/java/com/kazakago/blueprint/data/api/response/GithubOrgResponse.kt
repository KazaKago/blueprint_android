package com.kazakago.blueprint.data.api.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubOrgResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("login")
    val name: String,
)
