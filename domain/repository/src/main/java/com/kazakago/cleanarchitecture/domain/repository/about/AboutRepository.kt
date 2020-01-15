package com.kazakago.cleanarchitecture.domain.repository.about

import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.model.state.State
import kotlinx.coroutines.flow.Flow

interface AboutRepository {

    fun subscribeAppInfo(): Flow<State<AppInfo>>

    fun subscribeDeveloperInfo(): Flow<State<DeveloperInfo>>

}
