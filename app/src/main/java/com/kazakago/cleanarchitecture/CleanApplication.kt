package com.kazakago.cleanarchitecture

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy
import com.kazakago.cleanarchitecture.di.applicationModule
import com.kazakago.cleanarchitecture.di.dataModule
import com.kazakago.cleanarchitecture.di.domainModule
import com.kazakago.cleanarchitecture.di.webModule

open class CleanApplication : Application(), KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(applicationModule(this@CleanApplication))
        import(domainModule())
        import(dataModule())
        import(webModule())
    }

}
