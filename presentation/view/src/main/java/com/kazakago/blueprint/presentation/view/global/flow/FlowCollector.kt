package com.kazakago.blueprint.presentation.view.global.flow

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.collectOnLifecycle(lifecycleOwner: LifecycleOwner, state: Lifecycle.State, crossinline action: suspend (value: T) -> Unit): Job {
    return lifecycleOwner.lifecycleScope.launch {
        lifecycleOwner.repeatOnLifecycle(state) {
            collect(action)
        }
    }
}

inline fun <T> Flow<T>.collectOnCreated(lifecycleOwner: LifecycleOwner, crossinline action: suspend (value: T) -> Unit): Job {
    return collectOnLifecycle(lifecycleOwner, Lifecycle.State.CREATED, action)
}

inline fun <T> Flow<T>.collectOnStarted(lifecycleOwner: LifecycleOwner, crossinline action: suspend (value: T) -> Unit): Job {
    return collectOnLifecycle(lifecycleOwner, Lifecycle.State.STARTED, action)
}

inline fun <T> Flow<T>.collectOnResumed(lifecycleOwner: LifecycleOwner, crossinline action: suspend (value: T) -> Unit): Job {
    return collectOnLifecycle(lifecycleOwner, Lifecycle.State.RESUMED, action)
}
