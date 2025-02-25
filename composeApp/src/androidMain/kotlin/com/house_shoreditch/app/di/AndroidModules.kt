package com.house_shoreditch.app.di

import com.house_shoreditch.app.util.AndroidPlatform
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

object AndroidModules {

    val androidModule = module {
        factory { AndroidPlatform(androidApplication()) }
    }
}
