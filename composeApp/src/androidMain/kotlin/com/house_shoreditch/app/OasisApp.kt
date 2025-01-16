package com.house_shoreditch.app

import android.app.Application
import com.house_shoreditch.app.di.AndroidModules
import com.house_shoreditch.app.di.OasisModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class OasisApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@OasisApp)
            modules(
                OasisModules.allModules
                    .plus(AndroidModules.androidModule)
            )
        }
    }
}
