package com.kazakago.cleanarchitecture.data.repository.hierarchy.about

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.global.dispatcher.FlowDispatcher
import com.kazakago.cleanarchitecture.data.repository.distributor.about.AboutDistributor
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.hierarchy.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.model.global.state.State
import com.kazakago.cleanarchitecture.domain.repository.hierarchy.about.AboutRepository
import kotlinx.coroutines.flow.Flow

internal class AboutRepositoryImpl(context: Context) : AboutRepository {

    private val aboutDistributor = AboutDistributor(context)

    override fun subscribeAppInfo(): Flow<State<AppInfo>> {
        return FlowDispatcher(
            fetch = { aboutDistributor.getAppInfo() }
        ).subscribe()
    }

    override fun subscribeDeveloperInfo(): Flow<State<DeveloperInfo>> {
        return FlowDispatcher(
            fetch = { aboutDistributor.getDeveloperInfo() }
        ).subscribe()
    }

}
