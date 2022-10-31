package com.kazakago.blueprint.presentation.hierarchy.about

import androidx.compose.runtime.Composable
import com.kazakago.blueprint.domain.model.about.AboutInfo
import com.kazakago.blueprint.presentation.global.util.LocalAboutRepository
import com.kazakago.blueprint.presentation.global.util.QueryResult
import com.kazakago.blueprint.presentation.global.util.produceQuery

@Composable
fun queryAboutInfo(): QueryResult<AboutInfo> {
    val repository = LocalAboutRepository.current
    return produceQuery(
        key = Unit,
        fetch = {
            val appInfo = repository.getAppInfo()
            val developerInfo = repository.getDeveloperInfo()
            AboutInfo(appInfo, developerInfo)
        },
    )
}
