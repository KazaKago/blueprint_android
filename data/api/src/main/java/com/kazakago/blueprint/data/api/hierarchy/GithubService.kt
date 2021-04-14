package com.kazakago.blueprint.data.api.hierarchy

import com.kazakago.blueprint.data.api.response.GithubOrgResponse
import com.kazakago.blueprint.data.api.response.GithubRepoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("organizations")
    suspend fun getOrgs(@Query("since") since: Long?, @Query("per_page") perPage: Int): List<GithubOrgResponse>

    @GET("orgs/{org}/repos")
    suspend fun getRepos(@Path("org") org: String, @Query("page") page: Int?, @Query("per_page") perPage: Int): List<GithubRepoResponse>
}
