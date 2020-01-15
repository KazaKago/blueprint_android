package com.kazakago.cleanarchitecture.data.repository.repository.about

import android.content.Context
import com.kazakago.cleanarchitecture.data.repository.dispatcher.FlowDispatcher
import com.kazakago.cleanarchitecture.data.repository.distributor.about.AboutDistributor
import com.kazakago.cleanarchitecture.domain.model.about.AppInfo
import com.kazakago.cleanarchitecture.domain.model.about.DeveloperInfo
import com.kazakago.cleanarchitecture.domain.model.state.State
import com.kazakago.cleanarchitecture.domain.repository.about.AboutRepository
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
