package com.kazakago.blueprint.domain.usecase.hierarchy.github

import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.repository.hierarchy.GithubRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

internal class RefreshGithubReposUseCaseImpl @Inject constructor(
    private val githubRepository: GithubRepository,
) : RefreshGithubReposUseCase {

    override suspend fun invoke(githubOrgName: GithubOrgName) {
        coroutineScope {
            listOf(
                async { githubRepository.refreshOrg(githubOrgName) },
                async { githubRepository.refreshRepos(githubOrgName) }
            )
        }.awaitAll()
    }
}
