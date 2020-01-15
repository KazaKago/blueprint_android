package com.kazakago.cleanarchitecture.repository.repository.about

import android.content.Context
import com.kazakago.cleanarchitecture.model.about.AppInfo
import com.kazakago.cleanarchitecture.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.model.state.State
import com.kazakago.cleanarchitecture.repository.about.AboutRepository
import com.kazakago.cleanarchitecture.repository.dispatcher.FlowDispatcher
import com.kazakago.cleanarchitecture.repository.distributor.about.AboutDistributor
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
