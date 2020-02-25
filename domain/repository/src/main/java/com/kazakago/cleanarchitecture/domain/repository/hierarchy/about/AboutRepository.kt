package com.kazakago.cleanarchitecture.domain.repository.hierarchy.about

import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import kotlinx.coroutines.flow.Flow

interface AboutRepository {

    fun subscribeAppInfo(): Flow<State<AppInfo>>

    fun subscribeDeveloperInfo(): Flow<State<DeveloperInfo>>

}
