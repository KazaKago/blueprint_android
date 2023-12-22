package com.kazakago.mockserver

import android.content.Context
import androidx.startup.Initializer
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MockServerInitializer : Initializer<Unit> {

    @OptIn(DelicateCoroutinesApi::class)
    override fun create(context: Context) {
        GlobalScope.launch {
            main()
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}
