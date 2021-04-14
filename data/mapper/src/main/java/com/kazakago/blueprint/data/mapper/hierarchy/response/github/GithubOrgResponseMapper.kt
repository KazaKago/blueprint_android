package com.kazakago.blueprint.data.mapper.hierarchy.response.github

import com.kazakago.blueprint.data.api.response.GithubOrgResponse
import com.kazakago.blueprint.data.memory.entity.GithubOrgEntity

class GithubOrgResponseMapper {

    fun map(response: GithubOrgResponse): GithubOrgEntity {
        return GithubOrgEntity(
            id = response.id,
            name = response.name,
        )
    }
}
