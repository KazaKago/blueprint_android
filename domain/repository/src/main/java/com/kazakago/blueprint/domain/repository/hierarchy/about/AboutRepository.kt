package com.kazakago.blueprint.domain.repository.hierarchy.about

import com.kazakago.blueprint.domain.model.global.state.State
import com.kazakago.blueprint.domain.model.hierarchy.about.AppInfo
import com.kazakago.blueprint.domain.model.hierarchy.about.DeveloperInfo
import kotlinx.coroutines.flow.Flow

interface AboutRepository {

    fun subscribeAppInfo(): Flow<State<AppInfo>>

    fun subscribeDeveloperInfo(): Flow<State<DeveloperInfo>>
}
