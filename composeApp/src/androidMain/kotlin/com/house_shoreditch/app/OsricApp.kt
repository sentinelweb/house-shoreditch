package com.house_shoreditch.app

import android.app.Application
import com.house_shoreditch.app.di.AndroidModules
import com.house_shoreditch.app.di.OsricModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OsricApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OsricApp)
            modules(
                OsricModules.allModules
                    .plus(AndroidModules.androidModule)
            )
        }
    }
}
