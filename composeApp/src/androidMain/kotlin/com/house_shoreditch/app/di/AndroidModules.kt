package com.house_shoreditch.app.di

import android.content.Context
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object AndroidModules {

    val androidModule = module {
        factory<Context> { androidApplication() }
    }
}
