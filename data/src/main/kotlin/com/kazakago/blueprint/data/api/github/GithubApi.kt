package com.kazakago.blueprint.data.api.github

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.path
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubApi @Inject constructor(
    private val httpClient: HttpClient,
) {

    suspend fun getOrgs(since: Long?, perPage: Int): List<GithubOrgResponse> {
        return httpClient.get {
            url { path("organizations") }
            parameter("since", since)
            parameter("per_page", perPage)
        }.body()
    }

    suspend fun getOrg(orgName: String): GithubOrgResponse {
        return httpClient.get {
            url { path("orgs/$orgName") }
        }.body()
    }

    suspend fun getRepos(orgName: String, page: Int?, perPage: Int): List<GithubRepoResponse> {
        return httpClient.get {
            url { path("orgs/$orgName/repos") }
            parameter("page", page)
            parameter("per_page", perPage)
        }.body()
    }
}
