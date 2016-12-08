package com.kazakago.cleanarchitecture.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Context context;

    public ApplicationModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context;
    }

}