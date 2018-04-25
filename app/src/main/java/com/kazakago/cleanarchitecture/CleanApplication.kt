package com.kazakago.cleanarchitecture

import android.app.Application
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.KodeinAware
import com.github.salomonbrys.kodein.lazy
import com.kazakago.cleanarchitecture.data.di.dataModule
import com.kazakago.cleanarchitecture.domain.di.domainModule
import com.kazakago.cleanarchitecture.presentation.di.presentationModule
import com.kazakago.cleanarchitecture.web.di.webModule

open class CleanApplication : Application(), KodeinAware {

    override val kodein: Kodein by Kodein.lazy {
        import(presentationModule(this@CleanApplication))
        import(domainModule())
        import(dataModule())
        import(webModule())
    }

}
