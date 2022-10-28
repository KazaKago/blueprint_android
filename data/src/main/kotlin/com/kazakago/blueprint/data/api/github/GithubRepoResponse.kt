package com.kazakago.blueprint.data.api.github

import com.kazakago.blueprint.domain.model.github.GithubRepo
import com.kazakago.blueprint.domain.model.github.GithubRepoId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.net.URL

@Serializable
data class GithubRepoResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("html_url")
    val htmlUrl: String
) {
    fun toModel(): GithubRepo {
        return GithubRepo(
            id = GithubRepoId(id),
            name = fullName,
            url = URL(htmlUrl),
        )
    }
}
