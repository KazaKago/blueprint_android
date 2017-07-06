package com.kazakago.cleanarchitecture.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(context: Context) {

    private val context: Context = context.applicationContext

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return context
    }

}