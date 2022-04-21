package com.kazakago.blueprint.data.mapper.response.github

import com.kazakago.blueprint.data.api.response.GithubRepoResponse
import com.kazakago.blueprint.data.cache.entity.GithubRepoEntity
import java.net.URL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepoResponseMapper @Inject constructor() {

    fun map(response: GithubRepoResponse): GithubRepoEntity {
        return GithubRepoEntity(
            id = response.id,
            fullName = response.fullName,
            htmlUrl = URL(response.htmlUrl),
        )
    }
}
