package com.kazakago.blueprint.presentation.view.global.flow

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

fun <T> Flow<T>.collectOnLifecycle(lifecycleOwner: LifecycleOwner, state: Lifecycle.State, action: FlowCollector<T>): Job {
    return lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(state) {
            collect(action)
        }
    }
}

fun <T> Flow<T>.collectOnCreated(lifecycleOwner: LifecycleOwner, action: FlowCollector<T>): Job {
    return collectOnLifecycle(lifecycleOwner, Lifecycle.State.CREATED, action)
}

fun <T> Flow<T>.collectOnStarted(lifecycleOwner: LifecycleOwner, action: FlowCollector<T>): Job {
    return collectOnLifecycle(lifecycleOwner, Lifecycle.State.STARTED, action)
}

fun <T> Flow<T>.collectOnResumed(lifecycleOwner: LifecycleOwner, action: FlowCollector<T>): Job {
    return collectOnLifecycle(lifecycleOwner, Lifecycle.State.RESUMED, action)
}
