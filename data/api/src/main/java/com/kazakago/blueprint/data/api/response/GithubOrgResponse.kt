package com.kazakago.blueprint.data.api.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GithubOrgResponse(
    @Json(name = "id")
    val id: Long,
    @Json(name = "login")
    val name: String,
)
