package com.kazakago.cleanarchitecture

import android.app.Application
import com.kazakago.cleanarchitecture.di.component.ApplicationComponent
import com.kazakago.cleanarchitecture.di.component.DaggerApplicationComponent
import com.kazakago.cleanarchitecture.di.module.ApplicationModule
import com.kazakago.cleanarchitecture.di.module.DataModule
import com.kazakago.cleanarchitecture.di.module.DomainModule
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * アプリケーションクラス
 *
 * @author kensuke
 */
open class CleanApplication : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
        initializeRealm()
    }

    private fun initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .domainModule(DomainModule())
                .dataModule(DataModule())
                .build()
    }

    private fun initializeRealm() {
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

}
