package com.kazakago.blueprint.presentation.global.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.kazakago.blueprint.data.api.github.GithubApi
import com.kazakago.blueprint.domain.repository.AboutRepository
import com.kazakago.blueprint.domain.repository.GithubRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface HiltEntryPoint {
    fun githubRepository(): GithubRepository
    fun aboutRepository(): AboutRepository
    fun githubApi(): GithubApi
}

@Composable
fun LocalDIProvider(content: @Composable () -> Unit) {
    val entryPoint = EntryPointAccessors.fromApplication(LocalContext.current, HiltEntryPoint::class.java)
    CompositionLocalProvider(
        LocalGithubRepository provides entryPoint.githubRepository(),
        LocalAboutRepository provides entryPoint.aboutRepository(),
        LocalGithubApi provides entryPoint.githubApi(),
        content = content,
    )
}

val LocalGithubApi = staticCompositionLocalOf<GithubApi> {
    error("not found")
}

val LocalAboutRepository = staticCompositionLocalOf<AboutRepository> {
    error("not found")
}

val LocalGithubRepository = staticCompositionLocalOf<GithubRepository> {
    error("not found")
}
