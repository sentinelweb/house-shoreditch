package com.house_shoreditch.app

import android.app.Application
import com.house_shoreditch.app.di.OsricModules

class OsricApp: Application() {

    override fun onCreate() {
        super.onCreate()
        OsricModules.initKoin()
    }
}
