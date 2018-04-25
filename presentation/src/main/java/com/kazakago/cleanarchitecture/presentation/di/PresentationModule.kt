package com.kazakago.cleanarchitecture.presentation.di

import android.content.Context
import com.github.salomonbrys.kodein.Kodein
import com.github.salomonbrys.kodein.bind
import com.github.salomonbrys.kodein.instance

fun presentationModule(context: Context) = Kodein.Module {
    bind<Context>() with instance(context)
}