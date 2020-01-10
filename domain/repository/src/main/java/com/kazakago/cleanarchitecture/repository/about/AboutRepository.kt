package com.kazakago.cleanarchitecture.repository.about

import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.model.state.StoreState
import kotlinx.coroutines.flow.Flow

interface AboutRepository {

    fun subscribeAppInfo(): Flow<StoreState<AppInfo>>

    fun subscribeDeveloperInfo(): Flow<StoreState<DeveloperInfo>>

}
