package com.kazakago.blueprint.data.mapper.hierarchy.entity.github

import com.kazakago.blueprint.data.memory.entity.GithubOrgEntity
import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.domain.model.github.GithubOrgId
import com.kazakago.blueprint.domain.model.github.GithubOrgName

class GithubOrgEntityMapper {

    fun map(entity: GithubOrgEntity): GithubOrg {
        return GithubOrg(
            id = GithubOrgId(entity.id),
            name = GithubOrgName(entity.name),
        )
    }
}
