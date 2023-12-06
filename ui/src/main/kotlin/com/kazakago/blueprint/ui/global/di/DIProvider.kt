package com.kazakago.blueprint.ui.global.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.kazakago.blueprint.data.api.todo.ToDoApi
import com.kazakago.blueprint.data.resource.AppInfoDao
import com.kazakago.blueprint.data.resource.DeveloperInfoDao
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

internal val LocalToDoApi = staticCompositionLocalOf<ToDoApi> {
    error("not found")
}
internal val LocalAppInfoDao = staticCompositionLocalOf<AppInfoDao> {
    error("not found")
}
internal val LocalDeveloperInfoDao = staticCompositionLocalOf<DeveloperInfoDao> {
    error("not found")
}

@EntryPoint
@InstallIn(SingletonComponent::class)
internal interface HiltEntryPoint {
    fun toDoApi(): ToDoApi
    fun appInfoDao(): AppInfoDao
    fun developerInfoDao(): DeveloperInfoDao
}

@Composable
fun DIProvider(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val entryPoint = remember(content) { EntryPointAccessors.fromApplication(context, HiltEntryPoint::class.java) }
    CompositionLocalProvider(
        LocalToDoApi provides entryPoint.toDoApi(),
        LocalAppInfoDao provides entryPoint.appInfoDao(),
        LocalDeveloperInfoDao provides entryPoint.developerInfoDao(),
        content = content,
    )
}
