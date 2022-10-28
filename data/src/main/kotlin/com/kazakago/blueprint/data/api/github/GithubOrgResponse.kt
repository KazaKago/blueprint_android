package com.kazakago.blueprint.data.api.github

import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgId
import com.kazakago.blueprint.domain.model.github.GithubOrgName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

@Serializable
data class GithubOrgResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("login")
    val name: String,
    @SerialName("avatar_url")
    val avatarUrl: String,
) {
    fun toModel(): GithubOrg {
        return GithubOrg(
            id = GithubOrgId(id),
            name = GithubOrgName(name),
            imageUrl = URL(avatarUrl),
        )
    }
}
