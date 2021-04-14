package com.kazakago.blueprint.data.mapper.hierarchy.response.github

import com.kazakago.blueprint.data.api.response.GithubRepoResponse
import com.kazakago.blueprint.data.memory.entity.GithubRepoEntity
import java.net.URL

class GithubRepoResponseMapper {

    fun map(response: GithubRepoResponse): GithubRepoEntity {
        return GithubRepoEntity(
            id = response.id,
            fullName = response.fullName,
            htmlUrl = URL(response.htmlUrl),
        )
    }
}
