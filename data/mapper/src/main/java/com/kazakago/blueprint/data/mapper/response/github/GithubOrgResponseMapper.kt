package com.kazakago.blueprint.data.mapper.response.github

import com.kazakago.blueprint.data.api.response.GithubOrgResponse
import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubOrgResponseMapper @Inject constructor() {

    fun map(response: GithubOrgResponse): GithubOrgEntity {
        return GithubOrgEntity(
            id = response.id,
            name = response.name,
        )
    }
}
