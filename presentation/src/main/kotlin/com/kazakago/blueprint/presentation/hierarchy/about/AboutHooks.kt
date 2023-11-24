package com.kazakago.blueprint.presentation.hierarchy.about

import androidx.compose.runtime.Composable
import com.kazakago.blueprint.domain.model.about.AboutInfo
import com.kazakago.blueprint.presentation.global.util.LocalAboutRepository
import com.kazakago.swr.compose.state.SWRState
import com.kazakago.swr.compose.useSWR

@Composable
fun useAboutInfo(): SWRState<String, AboutInfo> {
    val repository = LocalAboutRepository.current
    return useSWR(
        key = "about",
        fetcher = {
            val appInfo = repository.getAppInfo()
            val developerInfo = repository.getDeveloperInfo()
            AboutInfo(appInfo, developerInfo)
        },
    )
}
