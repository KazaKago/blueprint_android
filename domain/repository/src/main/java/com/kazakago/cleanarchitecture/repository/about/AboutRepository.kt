package com.kazakago.cleanarchitecture.repository.about

import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.model.state.State
import kotlinx.coroutines.flow.Flow

interface AboutRepository {

    fun subscribeAppInfo(): Flow<State<AppInfo>>

    fun subscribeDeveloperInfo(): Flow<State<DeveloperInfo>>

}
