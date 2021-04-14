package com.kazakago.blueprint.domain.usecase.output.about

import com.kazakago.blueprint.domain.model.about.AppInfo
import com.kazakago.blueprint.domain.model.about.DeveloperInfo
import java.io.Serializable

data class AboutOutput(
    val appInfo: AppInfo,
    val developerInfo: DeveloperInfo
) : Serializable
