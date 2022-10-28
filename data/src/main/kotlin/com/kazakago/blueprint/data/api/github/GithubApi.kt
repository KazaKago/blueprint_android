package com.kazakago.blueprint.data.api.github

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("organizations")
    suspend fun getOrgs(@Query("since") since: Long?, @Query("per_page") perPage: Int): List<GithubOrgResponse>

    @GET("orgs/{org_name}")
    suspend fun getOrg(@Path("org_name") orgName: String): GithubOrgResponse

    @GET("orgs/{org_name}/repos")
    suspend fun getRepos(@Path("org_name") orgName: String, @Query("page") page: Int?, @Query("per_page") perPage: Int): List<GithubRepoResponse>
}
