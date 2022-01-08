package com.kazakago.blueprint.data.mapper.entity.github

import com.kazakago.blueprint.data.cache.entity.GithubOrgEntity
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgId
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubOrgEntityMapper @Inject constructor() {

    fun map(entity: GithubOrgEntity): GithubOrg {
        return GithubOrg(
            id = GithubOrgId(entity.id),
            name = GithubOrgName(entity.name),
        )
    }

    fun map(entities: List<GithubOrgEntity>): List<GithubOrg> {
        return entities.map { map(it) }
    }
}
