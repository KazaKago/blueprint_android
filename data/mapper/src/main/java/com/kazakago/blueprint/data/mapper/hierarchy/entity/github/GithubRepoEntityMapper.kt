package com.kazakago.blueprint.data.mapper.hierarchy.entity.github

import com.kazakago.blueprint.data.memory.entity.GithubRepoEntity
import com.kazakago.blueprint.domain.model.github.GithubRepo
import com.kazakago.blueprint.domain.model.github.GithubRepoId

class GithubRepoEntityMapper {

    fun map(entity: GithubRepoEntity): GithubRepo {
        return GithubRepo(
            id = GithubRepoId(entity.id),
            name = entity.fullName,
            url = entity.htmlUrl,
        )
    }
}
