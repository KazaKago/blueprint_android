package com.kazakago.blueprint.data.mapper.entity.github

import com.kazakago.blueprint.data.cache.entity.GithubRepoEntity
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepoId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepoEntityMapper @Inject constructor() {

    fun map(entity: GithubRepoEntity): GithubRepo {
        return GithubRepo(
            id = GithubRepoId(entity.id),
            name = entity.fullName,
            url = entity.htmlUrl,
        )
    }
}
